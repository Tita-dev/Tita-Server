package com.example.jwt.model.comments.dto;

import com.example.jwt.model.comments.Comments;
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
