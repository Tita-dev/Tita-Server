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
    public List<ForumDto> getForumList() throws Exception {
        List<Forum> forums = forumRepository.findAll();
        List<ForumDto> forumList = new ArrayList<>();

        for (Forum forum : forums){
            ForumDto forumDto = ForumDto.builder()
                    .forumName(forum.getForumName())
                    .explanation(forum.getExplanation())
                    .build();
            forumList.add(forumDto);
        }

        return forumList;
    }

    @Override
    public void forumCreate(ForumDto forumDto) throws Exception {
        if (forumRepository.findByForumName(forumDto.getForumName()) != null){
            throw new Exception();
        }
        forumRepository.save(forumDto.toEntity());
    }

    @Override
    public void forumDelete(ForumDto forumDto) throws Exception {

    }

    @Override
    public void forumPut(ForumChangeDto forumChangeDto) throws Exception {

    }
}
