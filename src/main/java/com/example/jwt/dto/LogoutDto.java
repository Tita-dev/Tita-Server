package com.example.jwt.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class LogoutDto {

    private String accessToken;

    private String refreshToken;
}
