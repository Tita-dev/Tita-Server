package com.example.jwt.repository;

import com.example.jwt.domain.Comments;
import com.example.jwt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentsRepository extends JpaRepository <Comments, Long> {
    List<Comments> findAllByPost(Post post);

    @Transactional
    void deleteByCommentsAndPost(String comments, Post post);

    Comments findByCommentsAndPost(String comments, Post post);
}
