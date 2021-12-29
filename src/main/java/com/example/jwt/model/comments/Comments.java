package com.example.jwt.model.comments;

import com.example.jwt.model.forum.Forum;
import com.example.jwt.model.post.Post;
import com.example.jwt.model.user.User;
import com.example.jwt.model.base_time.BaseEntity;
import lombok.*;

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
    private Forum forum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postIdx")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private User user;

    @Column(name = "comments_content", nullable = false)
    private String commentsContent;
}