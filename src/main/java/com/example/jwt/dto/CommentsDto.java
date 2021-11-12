package com.example.jwt.dto;

import com.example.jwt.domain.Comments;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentsDto {

    private String comments;

    public Comments toEntity(){
        return Comments.builder()
                .comments(this.getComments())
                .build();
    }

}
