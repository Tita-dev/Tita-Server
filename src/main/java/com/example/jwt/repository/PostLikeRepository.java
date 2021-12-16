package com.example.jwt.repository;

import com.example.jwt.domain.Post;
import com.example.jwt.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    PostLike findAllByPost (Post post);
}
