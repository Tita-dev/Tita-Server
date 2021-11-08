package com.example.jwt.service;

import com.example.jwt.domain.Forum;
import com.example.jwt.domain.Post;
import com.example.jwt.dto.ForumChangeDto;
import com.example.jwt.dto.ForumDto;
import com.example.jwt.dto.PostDto;
import com.example.jwt.repository.ForumRepository;
import com.example.jwt.repository.PostRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumServiceMpl implements ForumService{

    private final ForumRepository forumRepository;
    private final PostRepository postRepository;


    @Override
    @Transactional
    public List<String> getForumList() throws Exception {
        List<Forum> forums = forumRepository.findAll();
        List<String> forumList = new ArrayList<>();

        for (Forum forum : forums){
            ForumDto forumDto = ForumDto.builder()
                    .forumName(forum.getForumName())
                    .explanation(forum.getExplanation())
                    .build();
            forumList.add(forumDto.getForumName());
        }
        return forumList;
    }

    @Override
    public Forum forumCreate(ForumDto forumDto) throws Exception {
        if (forumRepository.existsByForumName(forumDto.getForumName()) == true){
            throw new Exception();
        }
        return forumRepository.save(forumDto.toEntity());
    }

    @Override
    public void forumDelete(ForumDto forumDto) throws Exception {
        if (forumRepository.existsByForumName(forumDto.getForumName()) == false){
            throw new Exception();
        }
        forumRepository.deleteByForumName(forumDto.getForumName());
    }

    @Override
    public void forumPut(ForumChangeDto forumChangeDto) throws Exception {
        if (forumRepository.existsByForumName(forumChangeDto.getForumName()) == false){
            throw new Exception();
        }
        Forum forum = forumRepository.findByForumName(forumChangeDto.getForumName());
        forum.setForumName(forumChangeDto.getNewForumName());
        forum.setExplanation(forumChangeDto.getNewExplanation());
        forumRepository.save(forum);
    }

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
}
