package com.example.jwt.service;

import com.example.jwt.model.forum.Forum;
import com.example.jwt.model.forum.dto.ForumChangeDto;
import com.example.jwt.model.forum.dto.ForumDto;
import com.example.jwt.model.forum.service.ForumService;
import com.example.jwt.model.forum.repository.ForumRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Disabled
class ForumServiceTest {

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private ForumService forumService;

    @DisplayName("게시판 목록 구현")
    @Test
    void getForumList() throws Exception {
        //given
        List<String> list;

        //when
        list = forumService.getForumList();

        //than
        for (String i : list) {
            System.out.println(i);
        }
    }

    @DisplayName("게시판 생성")
    @Test
    void forumCreate() throws Exception {
        //given
        ForumDto forumDto = ForumDto.builder()
                .forumName("민경ㅇㅇㅇㅇㅇㅇ")
                .explanation("민민민민")
                .build();
        //when
        Forum forum = forumService.forumCreate(forumDto);
        //then
        assertEquals(forumDto.getForumName(), forum.getForumName());
    }

    @DisplayName("게시판 삭제")
    @Test
    void forumDelete() throws Exception {
        //given
        ForumDto forumDto = ForumDto.builder()
                .forumName("민경모모모모모모모")
                .explanation("민민민민")
                .build();
        //when
        forumService.forumDelete(forumDto);

        //then
        assertEquals(false, forumRepository.existsByForumName(forumDto.getForumName()));
    }

    @DisplayName("게시판 수정")
    @Test
    void forumPut() throws Exception {
        //given
        ForumChangeDto forumChangeDto = new ForumChangeDto();
        forumChangeDto.setForumName("민경모민경모");
        forumChangeDto.setNewForumName("민경모씹떢");
        forumChangeDto.setNewExplanation("최윤성 개씹덕");

        //when
        Forum forum = forumService.forumPut(forumChangeDto);

        //then
        assertEquals(forumChangeDto.getNewForumName(), forum.getForumName());
    }
}