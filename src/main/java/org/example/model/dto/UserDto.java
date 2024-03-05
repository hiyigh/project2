package org.example.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.User;
import org.example.model.entity.shop.ImageFile;

public class UserDto {
    @Getter
    @Setter
    public static class Request {
        private String name;
        private String email;
        private String password;
    }
    @Getter
    public static class Response {
        private int userId;
        private String name;
        private String email;
        private String role;
        private ImageFile imageFile;
    }

    public static UserDto.Response toUserDtoResponse(User user, ImageFile imageFile) {
        UserDto.Response response = new Response();
        response.userId = user.getUserId();
        response.name = user.getName();
        response.email = user.getEmail();
        response.role = user.getRole();
        response.imageFile = imageFile;

        return response;
    }
}
