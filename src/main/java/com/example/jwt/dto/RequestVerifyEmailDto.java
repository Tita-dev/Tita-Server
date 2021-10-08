package com.example.jwt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestVerifyEmailDto {
    private String username;
    private String email;

}
