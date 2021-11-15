package com.example.jwt.controller;

import com.example.jwt.dto.PostChangeDto;
import com.example.jwt.dto.PostDto;
import com.example.jwt.service.ForumService;
import com.example.jwt.service.PostService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@Transactional
class PostControllerTest {

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;

    @BeforeEach
    public void Before(@Autowired PostController postController){
        mvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    @DisplayName("특정 게시판의 게시글들 가져오기")
    void postList() throws Exception {
        final ResultActions resultActions = mvc.perform(get("/tita/forum/{forumName}/list", "민경모씹떢")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("특정 게시판에 게시글 쓰기")
    void postCreate() throws Exception{
        //given
        PostDto postDto = PostDto.builder()
                .postName("으악")
                .content("나죽어")
                .build();

        String content = objectMapper.writeValueAsString(postDto);

        final ResultActions resultActions = mvc.perform(post("/tita/forum/{forumName}/create","민경모씹떢")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("특정 게시판의 게시글 지우기")
    void postDelete() throws Exception{
        //given
        PostDto postDto = PostDto.builder()
                .postName("민경모 에밀리아")
                .content("를 좋아하냐?")
                .build();

        String content = objectMapper.writeValueAsString(postDto);

        final ResultActions resultActions = mvc.perform(delete("/tita/forum/{forumName}/delete", "민경모씹떢")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("특정 게시판의 게시글 수정")
    void postPut() throws Exception{
        //given
        PostChangeDto postChangeDto = new PostChangeDto();
        postChangeDto.setPostName("민경모는 에밀리아");
        postChangeDto.setNewPostName("최윤성은 에밀리아");
        postChangeDto.setNewContent("를 사랑하냐??");

        String content = objectMapper.writeValueAsString(postChangeDto);

        final ResultActions resultActions = mvc.perform(put("/tita/forum/{forumName}/put", "민경모씹떢")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }
}