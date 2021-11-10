package com.example.jwt.domain;

import javax.persistence.*;

@Entity
public class Comments extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_idx")
    private Long commentsIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postIdx")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private User user;

    @Column(name = "comments", nullable = false)
    private String comments;
}
