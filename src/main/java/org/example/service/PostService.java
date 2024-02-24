package org.example.service;

import org.example.model.dto.board.PostDto;
import org.example.model.entity.board.Post;

import java.util.List;
import java.util.Map;

public interface PostService {
    void savePost(Post post);
    void deletePost(int postId);
    void editPost(Post post);
    List<PostDto> getPostList(int postCategoryId);
    Map<String, Object> getPostByPostId(int post_id);
    Map<String, Object> getPostByKeyword(String keyword);
    List<Map<String, Object>> getMyPostList(int user_id);

    int addLikeCount(int postId);
}
