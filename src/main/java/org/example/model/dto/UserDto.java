package org.example.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.model.enums.Role;
@Getter
@Setter
public class UserDto {
    private int user_id;
    private String user_name;
    private String user_img;
    private Role role;
    public void updateUserName(String user_name) {
        this.user_name = user_name;
    }
    public void updateUserImg(String user_img) {
        this.user_img = user_img;
    }
}
