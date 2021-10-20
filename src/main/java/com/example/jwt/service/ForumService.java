package com.example.jwt.service;


import com.example.jwt.dto.ForumChangeDto;
import com.example.jwt.dto.ForumDto;

import java.util.List;

public interface ForumService {
    List<ForumDto> getForumList() throws Exception;

    void forumCreate(ForumDto forumDto) throws Exception;

    void forumDelete(ForumDto forumDto) throws Exception;

    void forumPut(ForumChangeDto forumChangeDto) throws Exception;
}
