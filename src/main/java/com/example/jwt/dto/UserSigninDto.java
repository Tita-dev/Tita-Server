package com.example.jwt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSigninDto {
    public String username;
    public String password;

    public UserSigninDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
