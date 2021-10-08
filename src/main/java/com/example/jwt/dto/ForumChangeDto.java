package com.example.jwt.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ForumChangeDto {
    private String forumName;
    private String newForumName;
    private String newExplanation;
}
