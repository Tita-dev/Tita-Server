package com.example.jwt.model.user.dto;

import com.example.jwt.model.user.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {

    private String username;

    private String password;

    private String name;

    private String school;

    private String email;

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .name(this.name)
                .school(this.school)
                .email(this.email)
                .build();
    }
}
