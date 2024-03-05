package org.example.model.entity;

import lombok.Builder;
import lombok.Getter;
import org.example.model.enums.Role;

@Getter
public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private int userImage;
    private String role;
    @Builder
    public User(String name,String email ,String password, int userImage, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userImage = userImage;
        this.role = role.toString();
    }
}
