package com.example.jwt.model.comments.like;

import com.example.jwt.model.base_time.BaseEntity;
import com.example.jwt.model.comments.Comments;
import com.example.jwt.model.user.User;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userIdx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "commentsIdx")
    private Comments comments;
}
