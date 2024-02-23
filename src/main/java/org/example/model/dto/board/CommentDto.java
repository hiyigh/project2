package org.example.model.dto.board;

import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.board.Comment;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private int comment_id;
    private int post_id;
    private String comment_writer;
    private String comment_content;
    private int comment_like;
    private int comment_parent_id;
    private LocalDateTime created_at;
}
