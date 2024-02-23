package org.example.model.dto.board;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {
    private int post_id;
    private int post_category_id;
    //repository mapping
    private String post_writer;
    private String post_title;
    private String post_content;
    private int post_hits;
    private int post_like;
    private LocalDateTime created_at;

    public static void postEdit() {

    }
}
