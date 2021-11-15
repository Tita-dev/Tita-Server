package com.example.jwt.service;

import com.example.jwt.domain.Comments;
import com.example.jwt.dto.CommentsDto;

import java.util.List;
import java.util.Map;

public interface CommentsService {

    List<Map<String,String>> getPostAndComments(Long postIdx) throws Exception;

    Comments commentsCreate(String forumName, Long postIdx, CommentsDto commentsDto) throws Exception;

    void commentsDelete(Long postIdx, CommentsDto commentsDto) throws Exception;
}
