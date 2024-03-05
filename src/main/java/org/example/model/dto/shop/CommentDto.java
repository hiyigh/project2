package org.example.model.dto.shop;

import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.shop.Comment;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;

public class CommentDto {
    @Getter
    @Setter
    public static class Request {
        private String name;
        private String comment;
        private int depth;
        private int parentId;
    }
    @Getter
    public static class Response {
        private int commentId;
        private int itemId;
        private String writer;
        private String comment;
        private int depth;
        private int parentId;
        private int likes;
        private boolean viewStatus;
        private String createdAt;
        public void updateComment(String comment) {
            this.comment = comment;
        }
        public void addLike() {
            this.likes += 1;
        }
        public void toggleViewStatus() {
            this.viewStatus = !this.viewStatus;
        }
    }
    public static CommentDto.Response toCommentDtoResponse(Comment comment) {
        CommentDto.Response response = new Response();
        response.commentId = comment.getCommentId();
        response.itemId = comment.getItemId();
        response.comment = comment.getComment();
        response.writer = comment.getWriter();
        response.depth = comment.getDepth();
        response.parentId = comment.getParentId();
        response.likes = comment.getLikes();
        response.viewStatus = comment.isViewStatus();
        response.createdAt = comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return response;
    }
}
