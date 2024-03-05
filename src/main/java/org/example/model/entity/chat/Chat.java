package org.example.model.entity.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Chat {
    private int chatId;
    private int roomId;
    private String sender;
    private String receiver;
    private String message;
    private LocalDateTime create_at = LocalDateTime.now();

    @Builder
    public Chat(int roomId, String sender, String receiver, String message) {
        this.roomId = roomId;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }
}
