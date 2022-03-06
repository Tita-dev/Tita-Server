package com.example.jwt.model.forum.service;

import com.example.jwt.model.forum.dto.ForumChangeDto;
import com.example.jwt.model.forum.dto.ForumDto;
import com.example.jwt.model.forum.Forum;
import com.example.jwt.model.comments.repository.CommentsRepository;
import com.example.jwt.model.forum.repository.ForumRepository;
import com.example.jwt.model.post.like.repository.PostLikeRepository;
import com.example.jwt.model.post.repository.PostRepository;
import com.example.jwt.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {

    private final ForumRepository forumRepository;
    private final CurrentUserUtil currentUserUtil;

    @Override
    @Transactional
    public List<String> getForumList() throws Exception {
        List<Forum> forums = forumRepository.findAll();
        List<String> forumList = new ArrayList<>();

        for (Forum forum : forums) {
            forumList.add(forum.getForumName());
        }
        return forumList;
    }

    @Override
    public Forum forumCreate(ForumDto forumDto) throws Exception {
        if (forumRepository.existsByForumName(forumDto.getForumName()) == true) {
            throw new Exception();
        }
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
        if (forum.getUser().getUserIdx() == currentUserUtil.getCurrentUser().getUserIdx())
            forumRepository.deleteByForumName(forumDto.getForumName());
        else throw new Exception();
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
