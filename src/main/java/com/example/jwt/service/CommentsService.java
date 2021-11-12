package com.example.jwt.service;

import com.example.jwt.domain.Comments;
import com.example.jwt.dto.CommentsDto;

import java.util.List;

public interface CommentsService {

    List<String> getPostAndComments(Long postIdx) throws Exception;

    Comments commentsCreate(Long postIdx, CommentsDto commentsDto) throws Exception;

    void commentsDelete(Long postIdx, CommentsDto commentsDto) throws Exception;
}
