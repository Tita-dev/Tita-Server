package com.example.jwt.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Forum {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_idx")
    private Long forumIdx;

    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "forum_name")
    private String forumName;

    @Column(name = "explanation")
    private String explanation;
}
