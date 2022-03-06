package com.example.jwt.model.comments.service;

import com.example.jwt.model.comments.Comments;
import com.example.jwt.model.comments.like.CommentsLike;
import com.example.jwt.model.comments.like.repository.CommentsLikeRepository;
import com.example.jwt.model.comments.repository.CommentsRepository;
import com.example.jwt.model.forum.Forum;
import com.example.jwt.model.forum.repository.ForumRepository;
import com.example.jwt.model.post.Post;
import com.example.jwt.model.comments.dto.CommentsDto;
import com.example.jwt.model.post.like.repository.PostLikeRepository;
import com.example.jwt.model.post.repository.PostRepository;
import com.example.jwt.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final ForumRepository forumRepository;
    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;
    private final CurrentUserUtil currentUserUtil;
    private final CommentsLikeRepository commentsLikeRepository;
    private final PostLikeRepository postLikeRepository;

    @Override
    public List<Map<String, String>> getPostAndComments(Long postIdx) throws Exception {
        Post post = postRepository.findByPostIdx(postIdx);
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
