package com.example.jwt.model.post.like.repository;

import com.example.jwt.model.post.Post;
import com.example.jwt.model.post.like.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    PostLike findAllByPost (Post post);

    Long countPostLikeByPost (Post post);
}
