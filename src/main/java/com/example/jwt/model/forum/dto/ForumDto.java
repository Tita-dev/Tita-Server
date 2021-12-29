package com.example.jwt.model.forum.dto;

import com.example.jwt.model.forum.Forum;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@NoArgsConstructor
@Builder
@Setter
@AllArgsConstructor
public class ForumDto {
    @NotBlank
    @Size(min = 4, max = 16)
    private String forumName;
    @Size(max = 500)
    private String explanation;


    public Forum toEntity() {
        return Forum.builder()
                .forumName(this.getForumName())
                .explanation(this.getExplanation())
                .build();
    }

}
