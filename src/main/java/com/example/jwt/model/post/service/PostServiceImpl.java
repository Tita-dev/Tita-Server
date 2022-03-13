package com.example.jwt.model.post.service;

import com.example.jwt.model.comments.repository.CommentsRepository;
import com.example.jwt.model.forum.Forum;
import com.example.jwt.model.forum.repository.ForumRepository;
import com.example.jwt.model.post.Post;
import com.example.jwt.model.post.dto.PostChangeDto;
import com.example.jwt.model.post.dto.PostDeleteDto;
import com.example.jwt.model.post.dto.PostDto;
import com.example.jwt.model.post.like.PostLike;
import com.example.jwt.model.post.like.repository.PostLikeRepository;
import com.example.jwt.model.post.repository.PostRepository;
import com.example.jwt.model.user.enum_type.UserRole;
import com.example.jwt.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final ForumRepository forumRepository;
    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;
    private final CurrentUserUtil currentUserUtil;
    private final PostLikeRepository postLikeRepository;

    @Override
    public List<Map<String, String>> getForumPostList(String forumName) throws Exception {
        List<Post> posts = postRepository.findAllByForum(forumRepository.findByForumName(forumName));
        List<Map<String, String>> postList = posts.stream().map(
                        post -> {
                            Map<String,String> map = Map.of("PostIdx", Long.toString(post.getPostIdx()),"Notice", String.valueOf(post.getNotice()),
                                    "PostName", post.getPostName(),"Content", post.getContent(),
                                    "PostLike",Long.toString(postLikeRepository.countPostLikeByPost(post)),
                                    "CommentsCount",Long.toString(commentsRepository.countCommentsByPost(post)));
                            return map;
                        })
                .collect(Collectors.toList());
        return postList;
    }

    @Override
    public Post postCreate(String forumName, PostDto postDto) throws Exception {
        Forum forum = forumRepository.findByForumName(forumName);
        Post post = postDto.toEntity();
        post.setForum(forum);
        post.setUser(currentUserUtil.getCurrentUser());
        return postRepository.save(post);
    }

    @Override
    public void postDelete(String forumName, PostDeleteDto postDto) throws Exception {
        Forum forum = forumRepository.findByForumName(forumName);
        Post post = postRepository.findByPostNameAndForum(postDto.getPostName(), forum);
        if (currentUserUtil.getCurrentUser().getRole() == UserRole.ROLE_SCHOOL_ADMIN)
            postRepository.deletePostByPostNameAndForum(postDto.getPostName(), forum);
        else if (post.getUser().getUserIdx() == currentUserUtil.getCurrentUser().getUserIdx())
            postRepository.deletePostByPostNameAndForum(postDto.getPostName(), forum);
        else throw new Exception();
    }

    @Override
    public Post postPut(String forumName, PostChangeDto postChangeDto) throws Exception {
        Forum forum = forumRepository.findByForumName(forumName);
        Post post = postRepository.findByPostNameAndForum(postChangeDto.getPostName(), forum);
        if (post.getUser().getUserIdx() == currentUserUtil.getCurrentUser().getUserIdx()) {
            post.setPostName(postChangeDto.getNewPostName());
            post.setContent(postChangeDto.getNewContent());
            return postRepository.save(post);
        }
        else throw new Exception();
    }

    @Override
    public PostLike postLike(String forumName, PostDto postDto) throws Exception {
        Forum forum = forumRepository.findByForumName(forumName);
        if (postRepository.findByPostNameAndForum(postDto.getPostName(), forum) == null){
            throw new Exception();
        }
        PostLike postLike = PostLike.builder()
                .post(postRepository.findByPostNameAndForum(postDto.getPostName(), forum))
                .user(currentUserUtil.getCurrentUser())
                .build();
        return postLikeRepository.save(postLike);
    }

    @Override
    public List<Map<String, String>> getBestPost() {
        List<Post> posts = postRepository.findTop5ByOrderByPostLikeList();
        List<Map<String, String>> postList = posts.stream().map(
                post -> {
                    Map<String,String> map = Map.of("PostName", post.getPostName(),"Content", post.getContent(),
                            "PostLike",Long.toString(postLikeRepository.countPostLikeByPost(post)));
                    return map;
                }
        ).collect(Collectors.toList());
        return postList;
    }

    @Override
    public List<Map<String, String>> getNoticePost() {
        List<Post> posts = postRepository.findAllByNoticeTrue();
        List<Map<String, String>> postList = new ArrayList<>();
        for (Post post : posts) {
            Map<String, String> map = new HashMap<>();
            map.put("PostName", post.getPostName());
            map.put("Content", post.getContent());
            map.put("PostLike",Long.toString(postLikeRepository.countPostLikeByPost(post)));
            postList.add(map);
        }
        return postList;
    }
}
