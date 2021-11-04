package com.example.jwt.domain;

import com.example.jwt.domain.BaseEntity;
import com.example.jwt.domain.Forum;
import com.example.jwt.domain.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_idx")
    private Long postIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forumIdx")
    private Forum forum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private User user;

    @Size(max = 100)
    @Column(name = "post_name", nullable = false)
    private String postName;

    @Size(min = 0, max = 500)
    @Column(name = "content", nullable = false)
    private String content;
}
