package com.example.jwt.model.forum.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ForumChangeDto {
    private String forumName;
    private String newForumName;
    private String newExplanation;
}
