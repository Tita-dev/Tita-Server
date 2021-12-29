package com.example.jwt.model.comments.service;

import com.example.jwt.model.comments.Comments;
import com.example.jwt.model.comments.like.CommentsLike;
import com.example.jwt.model.comments.dto.CommentsDto;

import java.util.List;
import java.util.Map;

public interface CommentsService {

    List<Map<String, String>> getPostAndComments(Long postIdx) throws Exception;

    Comments commentsCreate(String forumName, Long postIdx, CommentsDto commentsDto) throws Exception;

    void commentsDelete(Long postIdx, CommentsDto commentsDto) throws Exception;

    CommentsLike commentsLike(Long postIdx, CommentsDto commentsDto) throws Exception;
}
