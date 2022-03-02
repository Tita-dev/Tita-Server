package com.example.jwt.model.post.like;

import com.example.jwt.model.base_time.BaseEntity;
import com.example.jwt.model.post.Post;
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
@Table(name = "postLike")
public class PostLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postLike_idx")
    private Long postLikeIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postIdx")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;
}
