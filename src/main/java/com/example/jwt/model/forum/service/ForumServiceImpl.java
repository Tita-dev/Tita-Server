package com.example.jwt.model.forum.service;

import com.example.jwt.model.forum.dto.ForumChangeDto;
import com.example.jwt.model.forum.dto.ForumDto;
import com.example.jwt.model.forum.Forum;
import com.example.jwt.model.forum.repository.ForumRepository;
import com.example.jwt.model.user.enum_type.UserRole;
import com.example.jwt.config.security.auth.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {

    private final ForumRepository forumRepository;
    private final CurrentUser currentUser;

    @Override
    @Transactional
    public List<String> getForumList() throws Exception {
        List<Forum> forums = forumRepository.findAll();
        List<String> forumList = forums.stream().map(
                forum -> {
                    return forum.getForumName();
                }
        ).collect(Collectors.toList());

        return forumList;
    }

    @Override
    public Forum forumCreate(ForumDto forumDto) throws Exception {
        if (forumRepository.existsByForumName(forumDto.getForumName()) == true) {
            throw new Exception();
        }
        Forum forum = forumDto.toEntity();
        forum.setUser(currentUser.getCurrentUser());
        return forumRepository.save(forum);
    }

    @Override
    public void forumDelete(ForumDto forumDto) throws Exception {
        if (forumRepository.existsByForumName(forumDto.getForumName()) == false) {
            throw new Exception();
        }
        Forum forum = forumRepository.findByForumName(forumDto.getForumName());
        if (currentUser.getCurrentUser().getRole() == UserRole.ROLE_SCHOOL_ADMIN)
            forumRepository.deleteByForumName(forumDto.getForumName());
        else if (forum.getUser().getUserIdx() == currentUser.getCurrentUser().getUserIdx())
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
