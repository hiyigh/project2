package org.example.model.dto.chat;

import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.chat.Chat;

import java.util.List;

public class ChatDto {
    @Getter
    @Setter
    public static class Send {
        private String sender;
        private String message;
    }
    @Getter
    @Setter
    public static class Receive {
        private int roomId;
        private String sender;
        private String receiver;
        private String message;
    }
    @Getter
    public static class ResponseList {
        private int roomId;
        private String sender;
        private String receiver;
        private List<String> message;
    }
    public static ChatDto.Receive toChatDtoReceive(Chat chat) {
        ChatDto.Receive receive = new Receive();
        receive.roomId = chat.getRoomId();
        receive.sender = chat.getSender();
        receive.receiver = chat.getReceiver();
        receive.message = chat.getMessage();
        return receive;
    }
    public static ChatDto.ResponseList toChatDtoList(List<Chat> chatList) {
        ChatDto.ResponseList list = new ResponseList();
        Chat chat = chatList.get(0);
        list.roomId = chat.getRoomId();
        list.sender = chat.getSender();
        list.receiver = chat.getReceiver();

        for (int i = 0; i < chatList.size(); ++i) {
            list.message.add(chatList.get(i).getMessage());
        }
        return list;
    }
}
