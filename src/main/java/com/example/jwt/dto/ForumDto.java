package com.example.jwt.dto;

import com.example.jwt.domain.Forum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@Builder
@Setter
public class ForumDto {
    private String forumName;
    private String explanation;


    public ForumDto (String forumName, String explanation){
        this.forumName = forumName;
        this.explanation = explanation;
    }

    public Forum toEntity(){
        return Forum.builder()
                .forumName(forumName)
                .explanation(explanation)
                .build();
    }

}
