package com.example.jwt.model.post;

import com.example.jwt.model.base_time.BaseEntity;
import com.example.jwt.model.forum.Forum;
import com.example.jwt.model.post.like.PostLike;
import com.example.jwt.model.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "post")
@DynamicInsert
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_idx")
    private Long postIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forumIdx")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Forum forum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Size(max = 100)
    @Column(name = "post_name", nullable = false)
    private String postName;

    @Size(min = 0, max = 500)
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "notice")
    @ColumnDefault("false")
    private Boolean notice = false;

    @OneToMany(mappedBy = "postLikeIdx", cascade = {CascadeType.ALL})
    private List<PostLike> postLikeList = new ArrayList<PostLike>();
}
