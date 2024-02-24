package org.example.model.dto.board;

import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.board.Post;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {
    private int post_id;
    private int post_category_id;
    //repository mapping
    private int post_writer;
    private String post_title;
    private String post_content;
    private int post_hits;
    private int post_like;
    private LocalDateTime created_at;

    public static Post toEntity(PostDto postDto) {
        Post post = Post.builder()
                .post_category_id(postDto.getPost_category_id())
                .post_writer(postDto.getPost_writer())
                .post_title(postDto.getPost_title())
                .post_content(postDto.getPost_content())
                .post_hits(postDto.getPost_hits())
                .post_like(postDto.getPost_like())
                .build();
        return post;
    }
    public static PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setPost_id(post.getPost_id());
        postDto.setPost_category_id(post.getPost_category_id());
        postDto.setPost_writer(post.getPost_writer());
        postDto.setPost_title(post.getPost_title());
        postDto.setPost_content(post.getPost_content());
        postDto.setPost_like(post.getPost_like());
        postDto.setPost_hits(post.getPost_hits());
        postDto.setCreated_at(post.getCreatedAt());
        return postDto;
    }
}
