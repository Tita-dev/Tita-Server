package com.example.jwt.model.post.repository;

import com.example.jwt.model.forum.Forum;
import com.example.jwt.model.post.Post;
import com.example.jwt.model.post.dto.PostDto;
import com.example.jwt.model.post.dto.PostResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository{
    List<Post> findAllByForum(Forum forum);

    @Transactional
    void deletePostByPostNameAndForum(String postName, Forum forum);

    @Transactional
    void deletePostByForum(Forum forum);

    Post findByPostNameAndForum(String postName, Forum forum);

    Post findByPostIdx(Long postIdx);

    List<Post> findTop5ByOrderByPostLikeList();

    //List<PostResponseDto> findPostPostLikeOrder();
}
