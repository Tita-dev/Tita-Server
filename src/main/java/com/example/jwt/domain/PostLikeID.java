package com.example.jwt.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class PostLikeID implements Serializable {
    private Long user;

    private Long post;
}
