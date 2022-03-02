package com.example.jwt.model.comments;

import com.example.jwt.model.forum.Forum;
import com.example.jwt.model.post.Post;
import com.example.jwt.model.user.User;
import com.example.jwt.model.base_time.BaseEntity;
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
@Table(name = "comments")
public class Comments extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_idx")
    private Long commentsIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forumIdx")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Forum forum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postIdx")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "comments_content", nullable = false)
    private String commentsContent;
}
