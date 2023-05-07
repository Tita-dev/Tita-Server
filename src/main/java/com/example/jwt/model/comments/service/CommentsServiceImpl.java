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
import com.example.jwt.model.user.enum_type.UserRole;
import com.example.jwt.config.security.auth.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final ForumRepository forumRepository;
    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;
    private final CurrentUser currentUser;
    private final CommentsLikeRepository commentsLikeRepository;
    private final PostLikeRepository postLikeRepository;

    @Override
    public List<Map<String, String>> getPostAndComments(Long postIdx) throws Exception {
        Post post = postRepository.findByPostIdx(postIdx);
        List<Comments> commentsList = commentsRepository.findAllByPost(post);
        Map<String, String> postMap = Map.of("Content", post.getContent(),"PostName", post.getPostName());
        List<Map<String, String>> commetsMapList = commentsList.stream().map(
                comments ->{
                    Map<String,String> map = Map.of("Comments", comments.getCommentsContent());
                    return map;
                }
        ).collect(Collectors.toList());
        commetsMapList.add(postMap);


        /*
        Map<String, String> commentsMap = new HashMap<>();
        postMap.put(;
        postMap.put();
        commetsMapList.add(postMap);
        for (Comments comments : commentsList) {
            commentsMap.put("Comments", comments.getCommentsContent());
            commetsMapList.add(commentsMap);
        }*/

        return commetsMapList;
    }

    @Override
    public Comments commentsCreate(String forumName, Long postIdx, CommentsDto commentsDto) throws Exception {
        Forum forum = forumRepository.findByForumName(forumName);
        Post post = postRepository.findByPostIdx(postIdx);
        Comments comments = commentsDto.toEntity();
        comments.setPost(post);
        comments.setForum(forum);
        comments.setUser(currentUser.getCurrentUser());
        return commentsRepository.save(comments);
    }

    @Override
    public void commentsDelete(Long postIdx, CommentsDto commentsDto) throws Exception {
        Post post = postRepository.findByPostIdx(postIdx);
        Comments comments = commentsRepository.findByCommentsContentAndPost(commentsDto.getCommentsContent(),post);
        if (currentUser.getCurrentUser().getRole() == UserRole.ROLE_SCHOOL_ADMIN)
            commentsRepository.deleteCommentsByCommentsContentAndPost(commentsDto.getCommentsContent(), post);
        else if (comments.getUser().getUserIdx() == currentUser.getCurrentUser().getUserIdx())
            commentsRepository.deleteCommentsByCommentsContentAndPost(commentsDto.getCommentsContent(), post);
        else throw new Exception();
    }

    @Override
    public CommentsLike commentsLike(Long postIdx, CommentsDto commentsDto) throws Exception {
        Post post = postRepository.findByPostIdx(postIdx);
        CommentsLike commentsLike = CommentsLike.builder()
                .user(currentUser.getCurrentUser())
                .comments(commentsRepository.findByCommentsContentAndPost(commentsDto.getCommentsContent(),post))
                .build();
        return commentsLikeRepository.save(commentsLike);
    }
}
