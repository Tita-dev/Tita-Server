package com.example.jwt.service;

import com.example.jwt.domain.Comments;
import com.example.jwt.domain.Post;
import com.example.jwt.dto.CommentsDto;
import com.example.jwt.repository.CommentsRepository;
import com.example.jwt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceMpl implements CommentsService{

    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;


    @Override
    public List<String> getPostAndComments(Long postIdx) throws Exception {
        Post post = postRepository.findByPostIdx(postIdx);
        List<Comments> commentsList = commentsRepository.findAllByPost(post);
        List<String> list = new ArrayList<>();
        list.add(post.getPostName());
        list.add(post.getContent());
        for (Comments comments : commentsList){
            CommentsDto commentsDto = CommentsDto.builder()
                    .commentsContent(comments.getCommentsContent())
                    .build();
            list.add(commentsDto.getCommentsContent());
        }
        return list;
    }

    @Override
    public Comments commentsCreate(Long postIdx, CommentsDto commentsDto) throws Exception {
        Post post = postRepository.findByPostIdx(postIdx);
        Comments comments = commentsDto.toEntity();
        comments.setPost(post);
        return commentsRepository.save(comments);
    }

    @Override
    public void commentsDelete(Long postIdx, CommentsDto commentsDto) throws Exception {
        Post post = postRepository.findByPostIdx(postIdx);
        commentsRepository.deleteCommentsByCommentsContentAndPost(commentsDto.getCommentsContent(), post);
    }
}
