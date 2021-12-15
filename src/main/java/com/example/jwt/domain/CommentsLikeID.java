package com.example.jwt.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class CommentsLikeID implements Serializable {
    public Long user;

    public Long comments;
}
