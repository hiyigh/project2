package org.example.service;

import org.example.model.dto.board.PostDto;
import org.example.model.entity.board.Post;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Multipart;

import java.util.List;
import java.util.Map;

public interface PostService {
    int savePost(int userId, PostDto.Write postDto, List<MultipartFile> imageList);
    void deletePost(int postId);
    void editPost(int postId,PostDto.Write postDto, List<MultipartFile> postFile, List<String> removeFile);
    List<PostDto.Load> getPostList(int postCategoryId);
    Map<String, Object> getPostByPostId(int post_id);
    Map<String, Object> getPostByKeyword(String keyword);
    List<Map<String, Object>> getMyPostList(int user_id);

    int setLikeCount(int postId);
}
