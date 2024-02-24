package org.example.controller.web;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.board.PostDto;
import org.example.model.entity.User;
import org.example.model.entity.board.Post;
import org.example.model.entity.util.Category;
import org.example.model.enums.CategoryType;
import org.example.service.CategoryService;
import org.example.service.PostService;
import org.example.service.UserService;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String savePost(PostDto postDto) {
        Post post = Post.builder()
                .post_category_id(postDto.getPost_category_id())
                .post_writer(postDto.getPost_writer())
                .post_title(postDto.getPost_title())
                .post_content(postDto.getPost_content())
                .post_like(postDto.getPost_like())
                .post_hits(postDto.getPost_hits())
                .build();
        postService.savePost(post);
        return "/board/list";
    }
    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return "success";
    }
    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable int postId, Model model) {
        Map<String, Object> postDto = postService.getPostByPostId(postId);
        model.addAttribute("postDto", postDto.get("postDto"));
        return "/board/edit";
    }
    @PostMapping("/edit")
    @ResponseBody
    public String editPost(@RequestBody PostDto postDto) {
        Post post = PostDto.toEntity(postDto);
        postService.editPost(post);
        return "success";
    }
    @GetMapping("/post/{id}")
    public String getPost(@PathVariable int postId, Model model) {
        Map<String, Object> postDto = postService.getPostByPostId(postId);
        model.addAttribute("postDto", postDto.get("postDto"));
        model.addAttribute("comments", postDto.get("comments"));
        return "/board/post";
    }

    @GetMapping("/list/{categoryId}")
    public String getPostList(@PathVariable int categoryId, Model model) {
        List<PostDto> postList = postService.getPostList(categoryId);
        model.addAttribute("postList", postList);
        return "/board/list";
    }
}
