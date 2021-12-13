package com.example.jwt.service;

import com.example.jwt.domain.Forum;
import com.example.jwt.domain.Post;
import com.example.jwt.domain.User;
import com.example.jwt.dto.ForumChangeDto;
import com.example.jwt.dto.ForumDto;
import com.example.jwt.dto.PostChangeDto;
import com.example.jwt.dto.PostDto;
import com.example.jwt.repository.CommentsRepository;
import com.example.jwt.repository.ForumRepository;
import com.example.jwt.repository.PostRepository;
import com.example.jwt.util.CurrentUserUtil;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumServiceMpl implements ForumService {

    private final ForumRepository forumRepository;
    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;
    private final CurrentUserUtil currentUserUtil;

    @Override
    @Transactional
    public List<String> getForumList() throws Exception {
        List<Forum> forums = forumRepository.findAll();
        List<String> forumList = new ArrayList<>();

        for (Forum forum : forums) {
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
        if (forumRepository.existsByForumName(forumDto.getForumName()) == true) {
            throw new Exception();
        }
        User user = currentUserUtil.getCurrentUser();
        System.out.println("asdasdasd");
        System.out.println(user.getUsername());
        System.out.println("asdasdasd");
        Forum forum = forumDto.toEntity();
        forum.setUser(currentUserUtil.getCurrentUser());
        return forumRepository.save(forum);
    }

    @Override
    public void forumDelete(ForumDto forumDto) throws Exception {
        if (forumRepository.existsByForumName(forumDto.getForumName()) == false) {
            throw new Exception();
        }
        Forum forum = forumRepository.findByForumName(forumDto.getForumName());
        commentsRepository.deleteCommentsByForum(forum);
        postRepository.deletePostByForum(forum);
        forumRepository.deleteByForumName(forumDto.getForumName());
    }

    @Override
    public Forum forumPut(ForumChangeDto forumChangeDto) throws Exception {
        if (forumRepository.existsByForumName(forumChangeDto.getForumName()) == false) {
            throw new Exception();
        }
        Forum forum = forumRepository.findByForumName(forumChangeDto.getForumName());
        forum.setForumName(forumChangeDto.getNewForumName());
        forum.setExplanation(forumChangeDto.getNewExplanation());
        return forumRepository.save(forum);
    }
}
