package com.example.jwt.model.post.service;

import com.example.jwt.model.post.Post;
import com.example.jwt.model.post.like.PostLike;
import com.example.jwt.model.post.dto.PostChangeDto;
import com.example.jwt.model.post.dto.PostDto;

import java.util.List;
import java.util.Map;

public interface PostService {
    List<Map<String, String>> getForumPostList(String forumName) throws Exception;

    Post postCreate(String forumName, PostDto postDto) throws Exception;

    void postDelete(String forumName, PostDto postDto) throws Exception;

    Post postPut(String forumName, PostChangeDto postChangeDto) throws Exception;

    PostLike postLike(String forumName, PostDto postDto) throws Exception;

    List<Map<String, String>> getBestPost();

    List<Map<String, String>> getNoticePost();
}
