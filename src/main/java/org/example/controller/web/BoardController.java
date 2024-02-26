package org.example.controller.web;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.UserDto;
import org.example.model.dto.board.PostDto;
import org.example.model.entity.User;
import org.example.model.entity.board.Post;
import org.example.model.entity.board.PostFile;
import org.example.model.entity.util.Category;
import org.example.model.enums.CategoryType;
import org.example.service.CategoryService;
import org.example.service.PostService;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final PostService postService;
    private final UserService userService;
    private final CategoryService categoryService;

    @GetMapping("/write")
    public String postWrite(AuthenticatedPrincipal principal, Model model) {
        User user = userService.getUserByEmail(principal.getName());
        List<Category> categoryList = categoryService.getCategoryList(CategoryType.POST);

        model.addAttribute("user", user);
        model.addAttribute("categoryList", categoryList);
        return "/board/write";
    }
    @PostMapping("/write")
    public ResponseEntity savePost(@RequestPart(value="images" , required = false)List<MultipartFile> imageList,
                                   @RequestPart(value="postWrite") PostDto.Write postDto,
                                   AuthenticatedPrincipal principal) {
        int userId = userService.getUserIdByEmail(principal.getName());
        int postId = postService.savePost(userId, postDto, imageList);
        return ResponseEntity.ok(postId);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("ok");
    }
    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable int postId, Model model) {
        Map<String, Object> postDto = postService.getPostByPostId(postId);
        model.addAttribute("postDto", postDto.get("postDto"));
        return "/board/edit";
    }
    @PutMapping("/edit")
    public ResponseEntity editPost(@RequestPart(value = "jsonData") PostDto.Write postDto,
                                   @RequestPart(value= "plusImageFile")List<MultipartFile> postFile,
                                   @RequestPart(value = "removeImageFile")List<String> removeFile,
                                   @AuthenticationPrincipal PrincipalDetails principalDetails) {

        postService.editPost(postDto, postFile, removeFile);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
    @GetMapping("/post/{id}")
    public ResponseEntity getPost(@PathVariable int postId, Model model) {
        Map<String, Object> map = postService.getPostByPostId(postId);
        Post post = (Post)map.get("post");

        UserDto userDto = userService.getUserById(post.getPost_writer());
        map.put("userDto", userDto);

        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @GetMapping(value = { "/list", "/list/{categoryId}"})
    public ResponseEntity getPostList(@PathVariable(required = false) int categoryId) {
        List<PostDto.Load> postList = postService.getPostList(categoryId);

        return ResponseEntity.status(HttpStatus.OK).body(postList);
    }
}
