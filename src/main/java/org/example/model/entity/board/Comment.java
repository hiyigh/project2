package org.example.model.entity.board;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.model.entity.util.CreatedAt;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends CreatedAt {
    private int comment_id;
    private int post_id;
    private int comment_writer;
    private String comment_content;
    private int comment_dept;
    private int comment_like;
    private int comment_parent_id;

    @Builder
    public Comment(int post_id, int comment_writer, String comment_content, int comment_dept,
                   int comment_like, int comment_parent_id) {
        this.post_id = post_id;
        this.comment_writer = comment_writer;
        this.comment_content = comment_content;
        this.comment_dept = comment_dept;
        this.comment_like = comment_like;
        this.comment_parent_id = comment_parent_id;
    }

    public void addLike() {
        this.comment_like += 1;
    }
    public void addDept() {
        this.comment_dept += 1;
    }
}
