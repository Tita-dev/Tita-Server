package com.example.jwt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostChangeDto {
    private String postName;
    private String NewPostName;
    private String NewContent;
}
