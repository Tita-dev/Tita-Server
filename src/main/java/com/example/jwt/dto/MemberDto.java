package com.example.jwt.dto;

import com.example.jwt.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder

public class MemberDto {

    private String username;

    private String password;

    private String name;

    private String school;

    private String email;

    public MemberDto(String username, String password, String name, String school, String email){
        this.username = username;
        this.password = password;
        this.name = name;
        this.school = school;
        this.email = email;
    }

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
