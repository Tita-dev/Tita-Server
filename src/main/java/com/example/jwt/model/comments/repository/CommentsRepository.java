package com.example.jwt.model.comments.repository;

import com.example.jwt.model.comments.Comments;
import com.example.jwt.model.forum.Forum;
import com.example.jwt.model.post.Post;
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
