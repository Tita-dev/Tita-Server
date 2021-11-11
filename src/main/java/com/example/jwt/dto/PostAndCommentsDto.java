package com.example.jwt.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PostAndCommentsDto {
    @NotBlank
    @Size(max = 100)
    private String postName;

    @Size(max = 500, min = 0)
    private String content;

    private List<String> comments;
}
