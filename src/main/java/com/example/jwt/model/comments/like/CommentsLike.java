package com.example.jwt.model.comments.like;

import com.example.jwt.model.base_time.BaseEntity;
import com.example.jwt.model.comments.Comments;
import com.example.jwt.model.user.User;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "commentsLike")
public class CommentsLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentsLike_id")
    private Long commentsLikeIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentsIdx")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Comments comments;
}
