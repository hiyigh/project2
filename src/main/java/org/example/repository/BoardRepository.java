package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.board.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final JdbcTemplate jdbcTemplate;

    public void savePost(Post post) {
        String sql="insert into Posts (post_category_id, post_writer, post_title, post_content, post_like, post_hits) " +
                "values (?,?,?,?,?,?)";

        jdbcTemplate.update(sql, post.getPost_category_id(), post.getPost_writer(), post.getPost_title(),
                post.getPost_content(), post.getPost_like(), post.getPost_hits());
    }
    public void deletePost(int postId) {
        String sql ="delete Posts where post_id = ?";
        jdbcTemplate.update(sql, postId);
    }
    public void editPost(Post post) {
        String sql = "update Posts set post_category_id = ?, post_title = ?, post_content = ? where post_id = ?";
        jdbcTemplate.update(sql, post.getPost_category_id(), post.getPost_title(), post.getPost_content(), post.getPost_id());
    }
    public List<Post> getPostList(int post_category_id) {
        if (post_category_id == -1) {

        }
    }
    public Post getPost() {
        // Comments 에서 댓글 같이 가져오기
    }
    public List<Post> getMyPostList() {

    }
}
