package com.example.jwt.repository;

import com.example.jwt.domain.Forum;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ForumRepository extends JpaRepository<Forum, Long> {
    Forum findByForumName(String forumName);
    Forum deleteByForumName(String forumName);
}
