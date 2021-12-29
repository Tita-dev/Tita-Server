package com.example.jwt.model.comments.like.repository;

import com.example.jwt.model.comments.like.CommentsLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsLikeRepository extends JpaRepository<CommentsLike, Long> {

}
