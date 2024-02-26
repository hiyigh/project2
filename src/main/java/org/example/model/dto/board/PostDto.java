package org.example.model.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.board.Post;

import java.time.LocalDateTime;

public class PostDto {
    @Getter
    @Setter
    public static class Write {
        private int post_category_id;
        private int post_writer;
        private String post_title;
        private String post_content;
        private int post_hits;
        private int post_like;
        private LocalDateTime created_at;

        @Builder
        public Write(int post_category_id, int post_writer, String post_title, String post_content,
                     int post_hits, int post_like) {
            this.post_category_id = post_category_id;
            this.post_writer = post_writer;
            this.post_title = post_title;
            this.post_content = post_content;
            this.post_hits = post_hits;
            this.post_like = post_like;
            this.created_at = LocalDateTime.now();
        }
    }
    @Getter
    @Setter
    public static class Load {
        private int post_id;
        private int post_category_id;
        private int post_writer;
        private String post_title;
        private String post_content;
        private int post_hits;
        private int post_like;
        private LocalDateTime created_at;
    }
    public static PostDto.Load toLoad(Post post){
        PostDto.Load postDto = new PostDto.Load();
        postDto.setPost_id(post.getPost_id());
        postDto.setPost_category_id(post.getPost_category_id());
        postDto.setPost_writer(post.getPost_writer());
        postDto.setPost_title(post.getPost_title());
        postDto.setPost_content(post.getPost_content());
        postDto.setPost_hits(post.getPost_hits());
        postDto.setPost_like(post.getPost_like());
        postDto.setCreated_at(post.getCreatedAt());
        return postDto;
    }
}
