package com.example.jwt.model.forum.service;

import com.example.jwt.model.forum.dto.ForumChangeDto;
import com.example.jwt.model.forum.dto.ForumDto;
import com.example.jwt.model.forum.Forum;

import java.util.List;

public interface ForumService {
    List<String> getForumList() throws Exception;

    Forum forumCreate(ForumDto forumDto) throws Exception;

    void forumDelete(ForumDto forumDto) throws Exception;

    Forum forumPut(ForumChangeDto forumChangeDto) throws Exception;
}