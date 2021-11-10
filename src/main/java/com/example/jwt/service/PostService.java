package com.example.jwt.service;

import com.example.jwt.domain.Post;
import com.example.jwt.dto.PostChangeDto;
import com.example.jwt.dto.PostDto;

import java.util.List;

public interface PostService {
    List<String> getForumPostList(String forumName) throws Exception;

    Post postCreate(String forumName, PostDto postDto) throws Exception;

    void postDelete(String forumName,PostDto postDto) throws Exception;

    Post postPut(String forumName, PostChangeDto postChangeDto) throws Exception;
}
