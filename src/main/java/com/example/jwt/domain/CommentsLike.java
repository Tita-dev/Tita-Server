package com.example.jwt.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "commentsLike")
@IdClass(CommentsLikeID.class)
public class CommentsLike extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    @Id
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentsIdx")
    @Id
    private Comments comments;
}
