package com.example.jwt.repository;

import com.example.jwt.domain.Forum;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ForumRepository extends JpaRepository<Forum, Long> {
}
