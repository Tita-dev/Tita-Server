package com.example.jwt.controller;

import com.example.jwt.model.comments.dto.CommentsDto;
import com.example.jwt.model.comments.controller.CommentsController;
import com.example.jwt.model.comments.service.CommentsService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@Transactional
class CommentsControllerTest {

    @MockBean
    CommentsService commentsService;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mvc;

    @BeforeEach
    public void Before(@Autowired CommentsController commentsController) {
        mvc = MockMvcBuilders.standaloneSetup(commentsController).build();
    }

    @Test
    @DisplayName("게시글 내용과 적힌 댓글 불러오기")
    void getPostAndComments() throws Exception {

        final ResultActions resultActions = mvc.perform(get("/tita/forum/{forumName}/{postIdx}", "민경모씹떢", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("댓글 작성")
    void commentsCreate() throws Exception {
        CommentsDto commentsDto = CommentsDto.builder()
                .commentsContent("카페에서 공부중")
                .build();

        String content = objectMapper.writeValueAsString(commentsDto);

        final ResultActions resultActions = mvc.perform(post("/tita/forum/{forumName}/{postIdx}/create", "민경모씹떢", "1")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("댓글 삭제")
    void postDelete() throws Exception {
        CommentsDto commentsDto = CommentsDto.builder()
                .commentsContent("민경모 개씹덕이네 ㄹㅇㅋㅋ")
                .build();

        String content = objectMapper.writeValueAsString(commentsDto);

        final ResultActions resultActions = mvc.perform(delete("/tita/forum/{forumName}/{postIdx}/delete", "민경모씹떢", "1")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }
}