package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.board.PostDto;
import org.example.model.entity.board.Post;
import org.example.repository.BoardRepository;
import org.example.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final BoardRepository boardRepository;
    @Override
    public void savePost(Post post) {
        boardRepository.savePost(post);
    }

    @Override
    public void deletePost(int postId) {
        boardRepository.deletePost(postId);
    }

    @Override
    public void editPost(Post post) {
        boardRepository.editPost(post);
    }

    @Override
    public List<PostDto> getPostList(int postCategoryId) {
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
    public int addLikeCount(int postId) {
        return boardRepository.addLikeCount(postId);
    }
}
