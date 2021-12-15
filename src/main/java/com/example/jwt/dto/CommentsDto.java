package com.example.jwt.dto;

import com.example.jwt.domain.Comments;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentsDto {

    private String commentsContent;

    public Comments toEntity() {
        return Comments.builder()
                .commentsContent(this.getCommentsContent())
                .build();
    }

}
