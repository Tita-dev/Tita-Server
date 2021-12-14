package com.example.jwt.service;

import com.example.jwt.domain.Forum;
import com.example.jwt.domain.Post;
import com.example.jwt.dto.PostChangeDto;
import com.example.jwt.dto.PostDto;
import com.example.jwt.repository.CommentsRepository;
import com.example.jwt.repository.ForumRepository;
import com.example.jwt.repository.PostRepository;
import com.example.jwt.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostServiceMpl implements PostService {

    private final ForumRepository forumRepository;
    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;
    private final CurrentUserUtil currentUserUtil;

    @Override
    public List<Map<String, String>> getForumPostList(String forumName) throws Exception {
        Forum forum = forumRepository.findByForumName(forumName);
        List<Post> posts = postRepository.findAllByForum(forum);
        List<Map<String, String>> postList = new ArrayList<>();

        for (Post post : posts) {
            PostDto postDto = PostDto.builder()
                    .postName(post.getPostName())
                    .content(post.getContent())
                    .build();

            Map<String, String> map = new HashMap<>();
            map.put("PostName", postDto.getPostName());
            map.put("Content", postDto.getContent());
            postList.add(map);
        }
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
    public void postDelete(String forumName, PostDto postDto) throws Exception {
        Forum forum = forumRepository.findByForumName(forumName);
        Post post = postRepository.findByPostNameAndForum(postDto.getPostName(), forum);
        commentsRepository.deleteCommentsByPost(post);
        postRepository.deletePostByPostNameAndForum(postDto.getPostName(), forum);
    }

    @Override
    public Post postPut(String forumName, PostChangeDto postChangeDto) throws Exception {
        Forum forum = forumRepository.findByForumName(forumName);
        Post post = postRepository.findByPostNameAndForum(postChangeDto.getPostName(), forum);
        post.setPostName(postChangeDto.getNewPostName());
        post.setContent(postChangeDto.getNewContent());
        return postRepository.save(post);
    }
}
