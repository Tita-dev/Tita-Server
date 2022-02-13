package com.example.jwt.model.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private String postName;

    private String content;

    @QueryProjection
    public PostResponseDto(String postName, String content){
        this.postName = postName;
        this.content = content;
    }
}
