package com.example.jwt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor


@Table(name ="forum")
public class Forum extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_idx")
    private Long forumIdx;

    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;

    @Column(name = "forum_name",unique = true,nullable = false)
    private String forumName;

    @Column(name = "explanation",nullable = true)
    private String explanation;
}
