package com.example.jwt.repository;

import com.example.jwt.domain.Forum;
import com.example.jwt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ForumRepository extends JpaRepository<Forum, Long> {
    Forum findByForumName(String forumName);

    @Transactional
    void deleteByForumName(String forumName);

    boolean existsByForumName(String forumName);

}
