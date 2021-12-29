package com.example.jwt.model.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class  PostChangeDto {
    private String postName;
    private String newPostName;
    private String newContent;
}
