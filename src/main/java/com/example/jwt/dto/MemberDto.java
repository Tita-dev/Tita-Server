package com.example.jwt.dto;

import com.example.jwt.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private String username;

    private String password;

    private String name;

    private String school;

    private String email;


    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .school(school)
                .email(email)
                .build();
    }
}
