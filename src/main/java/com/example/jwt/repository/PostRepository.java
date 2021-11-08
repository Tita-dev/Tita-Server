package com.example.jwt.repository;

import com.example.jwt.domain.Forum;
import com.example.jwt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByForum (Forum forum);
}
