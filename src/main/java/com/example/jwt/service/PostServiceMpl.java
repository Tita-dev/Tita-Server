package com.example.jwt.service;

import com.example.jwt.domain.Forum;
import com.example.jwt.domain.Post;
import com.example.jwt.dto.PostChangeDto;
import com.example.jwt.dto.PostDto;
import com.example.jwt.repository.ForumRepository;
import com.example.jwt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceMpl implements PostService{

    private final ForumRepository forumRepository;
    private final PostRepository postRepository;

    @Override
    public List<String> getForumPostList(String forumName) throws Exception {
        Forum forum =forumRepository.findByForumName(forumName);
        List<Post> posts = postRepository.findAllByForum(forum);
        List<String> postList = new ArrayList<>();

        for (Post post : posts){
            PostDto postDto = PostDto.builder()
                    .postName(post.getPostName())
                    .content(post.getContent())
                    .build();
            postList.add(postDto.getPostName());
            postList.add(postDto.getContent());
        }
        return postList;
    }

    @Override
    public Post postCreate(String forumName,PostDto postDto) throws Exception {
        Forum forum = forumRepository.findByForumName(forumName);
        Post post = postDto.toEntity();
        post.setForum(forum);
        return postRepository.save(post);
    }

    @Override
    public void postDelete(String forumName, PostDto postDto) throws Exception {
        Forum forum = forumRepository.findByForumName(forumName);

        postRepository.deletePostByPostNameAndForum(postDto.getPostName(),forum);
    }

    @Override
    public Post postPut(String forumName, PostChangeDto postChangeDto) throws Exception {
        Forum forum = forumRepository.findByForumName(forumName);
        Post post = postRepository.findByPostNameAndForum(postChangeDto.getPostName(),forum);
        post.setPostName(postChangeDto.getNewPostName());
        post.setContent(postChangeDto.getNewContent());
        return postRepository.save(post);
    }
}
