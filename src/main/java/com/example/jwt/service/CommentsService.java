package com.example.jwt.service;

import com.example.jwt.domain.Comments;
import com.example.jwt.dto.CommentsDto;

import java.util.List;

public interface CommentsService {

    List<String> getPostAndComments(String forumName, Long postIdx) throws Exception;

    Comments commentsCreate(String forumName, Long postIdx, CommentsDto commentsDto) throws Exception;

    void commentsDelete(String forumName, Long postIdx, CommentsDto commentsDto) throws Exception;
}
