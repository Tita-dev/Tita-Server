package com.example.jwt.controller;

import com.example.jwt.model.forum.dto.ForumChangeDto;
import com.example.jwt.model.forum.dto.ForumDto;
import com.example.jwt.model.forum.controller.ForumController;
import com.example.jwt.model.forum.service.ForumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@Transactional
@Slf4j
class ForumControllerTest {

    @MockBean
    private ForumService forumService;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void Before(@Autowired ForumController forumController) {
        mvc = MockMvcBuilders.standaloneSetup(forumController).build();
    }

    @Test
    @DisplayName("게시판 목록 불러오기")
    void forumList() throws Exception {

        final ResultActions resultActions = mvc.perform(get("/tita/forum/list")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andDo(print())
                .andExpect(status().isOk());

//            MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
//            list = mvcResult.getResponse().getContentType();
    }

    @Test
    @DisplayName("게시판 생성")
    void forumCreate() throws Exception {
        //given
        ForumDto forumDto = ForumDto.builder()
                .forumName("최윤성성")
                .explanation("최최최최")
                .build();

        String content = objectMapper.writeValueAsString(forumDto);

        final ResultActions resultActions = mvc.perform(post("/tita/forum/create")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("게시판 삭제")
    void forumDelete() throws Exception {
        //given
        ForumDto forumDto = ForumDto.builder()
                .forumName("최윤성성")
                .explanation("최최최최")
                .build();

        String content = objectMapper.writeValueAsString(forumDto);

        final ResultActions resultActions = mvc.perform(delete("/tita/forum/delete")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("게시판 수정")
    void forumPut() throws Exception {
        //given
        ForumChangeDto forumDto = new ForumChangeDto();
        forumDto.setForumName("최윤성성");
        forumDto.setNewForumName("박상숸숸");
        forumDto.setNewExplanation("박박박박박");

        String content = objectMapper.writeValueAsString(forumDto);

        final ResultActions resultActions = mvc.perform(put("/tita/forum/put")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }
}