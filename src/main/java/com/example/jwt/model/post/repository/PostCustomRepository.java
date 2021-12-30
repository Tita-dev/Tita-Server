package com.example.jwt.model.post.repository;

import com.example.jwt.model.post.dto.PostDto;

import java.util.List;

public interface PostCustomRepository {

    List<PostDto> findPostPostLikeOrder();
}
