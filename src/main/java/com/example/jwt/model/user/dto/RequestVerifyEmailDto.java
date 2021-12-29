package com.example.jwt.model.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestVerifyEmailDto {
    private String username;
    private String email;

}
