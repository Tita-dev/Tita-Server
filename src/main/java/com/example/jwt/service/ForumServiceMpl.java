package com.example.jwt.service;

import com.example.jwt.domain.Forum;
import com.example.jwt.dto.ForumChangeDto;
import com.example.jwt.dto.ForumDto;
import com.example.jwt.repository.ForumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumServiceMpl implements ForumService{

    private final ForumRepository forumRepository;


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
}
