package org.example.model.entity.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.model.dto.board.PostDto;
import org.example.model.entity.util.CreatedAt;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class Post extends CreatedAt {
    private int post_id;
    private int post_category_id;
    private int post_writer;
    private int post_like;
    private int post_hits;
    private String post_title;
    private String post_content;
    @Builder
    public Post(int post_id, int post_category_id, int post_writer,
                String post_title, String post_content, int post_like, int post_hits, LocalDateTime created_at){
        this.post_id = post_id;
        this.post_category_id = post_category_id;
        this.post_writer = post_writer;
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_like = post_like;
        this.post_hits = post_hits;
        this.createdAt = created_at;
    }
    public void updatePost(PostDto.Write postDto) {
        this.post_category_id = postDto.getPost_category_id();
        this.post_title = postDto.getPost_title();
        this.post_content = postDto.getPost_content();
    }
    public void addLike() {
        this.post_like += 1;
    }
    public void addHits() {
        this.post_hits += 1;
    }
}
