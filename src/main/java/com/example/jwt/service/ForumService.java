package com.example.jwt.service;


import com.example.jwt.domain.Forum;
import com.example.jwt.domain.Post;
import com.example.jwt.dto.ForumChangeDto;
import com.example.jwt.dto.ForumDto;
import com.example.jwt.dto.PostChangeDto;
import com.example.jwt.dto.PostDto;

import java.util.List;

public interface ForumService {
    List<String> getForumList() throws Exception;

    Forum forumCreate(ForumDto forumDto) throws Exception;

    void forumDelete(ForumDto forumDto) throws Exception;

    Forum forumPut(ForumChangeDto forumChangeDto) throws Exception;
}
