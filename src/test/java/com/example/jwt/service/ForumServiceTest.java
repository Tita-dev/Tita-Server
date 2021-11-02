package com.example.jwt.service;

import com.example.jwt.dto.ForumDto;
import com.example.jwt.repository.ForumRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ForumServiceTest {

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private ForumService forumService;



    @DisplayName("게시판 목록 구현")
    @Test
    void getForumList() {
        
    }

    @DisplayName("게시판 생성")
    @Test
    void forumCreate() throws Exception{
        //given
        ForumDto forumDto = ForumDto.builder()
                .forumName("민경모모모모모모모")
                .explanation("민민민민")
                .build();
        //when
        forumService.forumCreate(forumDto);
        //then
        assertEquals(true,(forumRepository.existsByForumName(forumDto.getForumName())));
    }

    @DisplayName("게시판 삭제")
    @Test
    void forumDelete() {
    }

    @DisplayName("게시판 수정")
    @Test
    void forumPut() {
    }
}