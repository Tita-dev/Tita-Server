package com.example.jwt.service;

import com.example.jwt.domain.Comments;
import com.example.jwt.domain.CommentsLike;
import com.example.jwt.domain.Forum;
import com.example.jwt.domain.Post;
import com.example.jwt.dto.CommentsDto;
import com.example.jwt.repository.*;
import com.example.jwt.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentsServiceMpl implements CommentsService {

    private final ForumRepository forumRepository;
    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;
    private final CurrentUserUtil currentUserUtil;
    private final CommentsLikeRepository commentsLikeRepository;
    private final PostLikeRepository postLikeRepository;

    @Override
    public List<Map<String, String>> getPostAndComments(Long postIdx) throws Exception {
        Post post = postRepository.findByPostIdx(postIdx);
        System.out.println(postLikeRepository.countPostLikeByPost(post));
        List<Comments> commentsList = commentsRepository.findAllByPost(post);
        List<Map<String, String>> commetsMapList = new ArrayList<>();
        Map<String, String> postMap = new HashMap<>();
        Map<String, String> commentsMap = new HashMap<>();
        postMap.put("Content", post.getContent());
        postMap.put("PostName", post.getPostName());
        for (Comments comments : commentsList) {
            commentsMap.put("Comments", comments.getCommentsContent());
            commetsMapList.add(commentsMap);
        }
        return commetsMapList;
    }

    @Override
    public Comments commentsCreate(String forumName, Long postIdx, CommentsDto commentsDto) throws Exception {
        Forum forum = forumRepository.findByForumName(forumName);
        Post post = postRepository.findByPostIdx(postIdx);
        Comments comments = commentsDto.toEntity();
        comments.setPost(post);
        comments.setForum(forum);
        comments.setUser(currentUserUtil.getCurrentUser());
        return commentsRepository.save(comments);
    }

    @Override
    public void commentsDelete(Long postIdx, CommentsDto commentsDto) throws Exception {
        Post post = postRepository.findByPostIdx(postIdx);
        commentsRepository.deleteCommentsByCommentsContentAndPost(commentsDto.getCommentsContent(), post);
    }

    @Override
    public CommentsLike commentsLike(Long postIdx, CommentsDto commentsDto) throws Exception {
        Post post = postRepository.findByPostIdx(postIdx);
        CommentsLike commentsLike = CommentsLike.builder()
                .user(currentUserUtil.getCurrentUser())
                .comments(commentsRepository.findByCommentsContentAndPost(commentsDto.getCommentsContent(),post))
                .build();
        return commentsLikeRepository.save(commentsLike);
    }
}
