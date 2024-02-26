package org.example.model.dto;

import lombok.Builder;
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
    @Builder
    public UserDto(int user_id, String user_name, String user_img, Role role){
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_img = user_name;
        this.role = role;
    }
    public void updateUserName(String user_name) {
        this.user_name = user_name;
    }
    public void updateUserImg(String user_img) {
        this.user_img = user_img;
    }
}
