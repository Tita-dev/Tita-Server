package com.example.jwt.model.post.dto;

import com.example.jwt.model.post.Post;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    @NotBlank
    @Size(max = 100)
    private String postName;

    @Size(max = 500, min = 0)
    private String content;

    private Boolean notice;

    public Post toEntity() {
        return Post.builder()
                .postName(this.getPostName())
                .content(this.getContent())
                .notice(this.getNotice())
                .build();
    }
}
