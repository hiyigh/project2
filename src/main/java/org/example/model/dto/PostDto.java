package org.example.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {
    private int post_id;
    private int post_category_id;
    private int post_writer;
    private String post_title;
    private String post_content;
    private int post_hits;
    private int post_like;
    private LocalDateTime createdAt;
}
