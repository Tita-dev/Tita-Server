package com.example.jwt.repository;

import com.example.jwt.domain.Forum;
import com.example.jwt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByForum (Forum forum);
    @Transactional
    void deletePostByPostNameAndForum(String postName,Forum forum);

    Post findByPostNameAndForum(String postName,Forum forum);

}
