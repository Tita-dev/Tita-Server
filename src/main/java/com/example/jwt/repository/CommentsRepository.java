package com.example.jwt.repository;

import com.example.jwt.domain.Comments;
import com.example.jwt.domain.Forum;
import com.example.jwt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByPost(Post post);

    @Transactional
    void deleteCommentsByCommentsContentAndPost(String comments, Post post);

    @Transactional
    void deleteCommentsByPost(Post post);

    @Transactional
    void deleteCommentsByForum(Forum forum);

    Comments findByCommentsContentAndPost(String commentsContent, Post post);

    Long countCommentsByPost(Post post);
}
