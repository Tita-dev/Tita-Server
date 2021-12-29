package com.example.jwt.repository;

import com.example.jwt.domain.CommentsLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsLikeRepository extends JpaRepository<CommentsLike, Long> {

}
