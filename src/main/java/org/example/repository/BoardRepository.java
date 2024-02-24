package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.board.CommentDto;
import org.example.model.dto.board.PostDto;
import org.example.model.entity.board.Comment;
import org.example.model.entity.board.Post;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<PostDto> getPostList(int post_category_id) {

        List<Post> rows = new ArrayList<>();
        List<PostDto> result = new ArrayList<>();
        if (post_category_id == -1) {
            String sql = "select * from Posts";
            rows = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
            for (int i = 0; i < rows.size(); ++i) {
                PostDto postDto = PostDto.toDto(rows.get(i));
                result.add(postDto);
            }

        } else {
            String sql = "select * from Posts where post_category_id = ?";
            rows = jdbcTemplate.query(sql, new Object[]{post_category_id}, new BeanPropertyRowMapper<>(Post.class));
            for (int i = 0; i < rows.size(); ++i) {
                PostDto postDto = PostDto.toDto(rows.get(i));
                result.add(postDto);
            }
        }
        return result;
    }
    public Map<String, Object> getPostByPostId(int post_id) {
        String sql = "select p.*,c.*,u.user_name as post_writer_name " +
                "from Posts p" +
                "left join Comments c on p.post_id = c.post_id" +
                "left join Users u on p.post_writer = u.user_id" +
                "where p.post_id = ?";
        List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql, post_id);
        return mapping(rows);

    }
    public Map<String, Object> getPostByKeyword(String keyword) {
        String sql = "select p.* c.* u.user_name as post_writer_name " +
                "from Posts p " +
                "left join Comments c on p.post_id = c.post_id " +
                "where p.post_title like ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, "%" + keyword + "%");
        return mapping(rows);
    }
    private Map<String, Object> mapping(List<Map<String,Object>> rows) {
        PostDto postDto = null;
        List<Comment> comments = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            if (postDto == null) {
                postDto = new PostDto();
                postDto.setPost_id((int)row.get("post_id"));
                postDto.setPost_category_id((int)row.get("post_category_id"));
                postDto.setPost_writer((int)row.get("post_writer_name"));
                postDto.setPost_title((String)row.get("post_title"));
                postDto.setPost_content((String)row.get("post_content"));
                postDto.setPost_like((int)row.get("post_like"));
                postDto.setPost_hits((int)row.get("post_hits"));
                postDto.setCreated_at((LocalDateTime)row.get("created_at"));
            }
            Comment comment = Comment.builder()
                    .comment_id((int)row.get("comment_id"))
                    .post_id((int)row.get("post_id"))
                    .comment_writer((int)row.get("comment_writer"))
                    .comment_content((String)row.get("comment_content"))
                    .comment_like((int)row.get("comment_like"))
                    .comment_dept((int)row.get("comment_dept"))
                    .comment_parent_id((int)row.get("comment_parent_id"))
                    .build();
            comments.add(comment);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("postDto", postDto);
        result.put("comments",comments);
        return result;
    }
    public List<Map<String, Object>> getMyPostList(int user_id) {
        String sql = "select post_id, post_title from Posts where post_writer = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, user_id);

        return rows;
    }

    public int addLikeCount(int postId) {
        String sql = "update Posts set post_like = post_like + 1";
        jdbcTemplate.update(sql);

        String sql2 = "select post_like from Posts where post_id = ?";
        Integer postLikeCount = jdbcTemplate.queryForObject(sql2, Integer.class, postId);
        return postLikeCount;
    }
}
