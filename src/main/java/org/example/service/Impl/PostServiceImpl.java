package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.board.PostDto;
import org.example.model.entity.board.Post;
import org.example.model.entity.board.PostFile;
import org.example.repository.BoardRepository;
import org.example.repository.ImageFileRepository;
import org.example.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final BoardRepository boardRepository;
    private final ImageFileRepository imageFileRepository;

    @Override
    public int savePost(int userId, PostDto.Write postDto, List<MultipartFile> imageList) {
        Post post = Post.builder()
                .post_category_id(postDto.getPost_category_id())
                .post_writer(postDto.getPost_writer())
                .post_title(postDto.getPost_title())
                .post_content(postDto.getPost_content())
                .post_hits(postDto.getPost_hits())
                .post_like(postDto.getPost_like())
                .build();

        int postId = boardRepository.savePost(post);

        if (imageList != null) postFileSave(postId ,imageList);

        return postId;
    }

    private void postFileSave(int postId, List<MultipartFile> imageList) {
        for (MultipartFile file : imageList) {
            UUID uuid = UUID.randomUUID();
            String filename = uuid + "_" +file.getOriginalFilename();
            Path savePath = Paths.get("static" + File.separator + "img" + File.separator + filename).toAbsolutePath();

            try{
                file.transferTo(savePath.toFile());
            }catch (IOException o){
                o.printStackTrace();
            }

            PostFile postFile = PostFile.builder()
                    .post_id(postId)
                    .img_file_name(filename)
                    .img_file_path("/static/img")
                    .build();

            imageFileRepository.savePostFile(postFile);
        }
    }

    @Override
    public void deletePost(int postId) {
        boardRepository.deletePost(postId);
    }

    @Override
    public void editPost(int postId, PostDto.Write postDto, List<MultipartFile> postFile, List<String> rmFile) {
        if (rmFile != null) {
            removeFile(postId, rmFile);
        }
        if (postFile != null) {
            postFileSave(postId, postFile);
        }
        boardRepository.editPost(postId, postDto);
    }
    private void removeFile(int postId, List<String> rmFileList) {
        for (String fileList: rmFileList) {
            String file = URLDecoder.decode(fileList, StandardCharsets.UTF_8);
            imageFileRepository.removeFile(file);
        }
    }

    @Override
    public List<PostDto.Load> getPostList(int postCategoryId) {
        return boardRepository.getPostList(postCategoryId);
    }

    @Override
    public Map<String, Object> getPostByPostId(int post_id) {
        return boardRepository.getPostByPostId(post_id);
    }

    @Override
    public Map<String, Object> getPostByKeyword(String keyword) {
        return boardRepository.getPostByKeyword(keyword);
    }

    @Override
    public List<Map<String, Object>> getMyPostList(int user_id) {
        return boardRepository.getMyPostList(user_id);
    }

    @Override
    public int setLikeCount(int postId) {
        return boardRepository.setLikeCount(postId);
    }
}
