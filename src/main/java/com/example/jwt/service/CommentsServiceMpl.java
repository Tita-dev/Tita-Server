package com.example.jwt.service;

import com.example.jwt.domain.Comments;
import com.example.jwt.dto.CommentsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceMpl implements CommentsService{



    @Override
    public List<String> getPostAndComments(String forumName, Long postIdx) throws Exception {
        return null;
    }

    @Override
    public Comments commentsCreate(String forumName, Long postIdx, CommentsDto commentsDto) throws Exception {
        return null;
    }

    @Override
    public void commentsDelete(String forumName, Long postIdx, CommentsDto commentsDto) throws Exception {

    }
}
