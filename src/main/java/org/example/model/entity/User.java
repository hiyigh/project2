package org.example.model.entity;

import lombok.Builder;
import lombok.Getter;
import org.example.model.enums.Role;

@Getter
public class User {
    private int user_id;
    private String user_name;
    private String user_email;
    private String user_password;
    private String user_img;
    private Role user_role;

    @Builder
    public User(String user_name,String user_email ,String user_password, String user_img, Role user_role) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_img = user_img;
        this.user_role = user_role;
    }

}
