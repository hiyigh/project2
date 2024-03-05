package org.example.model.entity.shop;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.model.entity.util.CreatedAt;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends CreatedAt {
    private int commentId;
    //foreign key
    private int itemId;
    //foreign key
    private String writer;
    private String comment;
    private int depth;
    private int parentId;
    private int likes;
    private boolean viewStatus;
    @Builder
    public Comment(int itemId, String writer, String comment, int depth, int parentId, int likes, boolean viewStatus) {
        this.itemId = itemId;
        this.writer = writer;
        this.comment = comment;
        this.depth = depth;
        this.parentId = parentId;
        this.likes = likes;
        this.viewStatus = viewStatus;
    }
    public void setViewStatus() {
        this.viewStatus = !this.viewStatus;
    }
}
