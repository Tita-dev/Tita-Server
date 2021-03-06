package com.example.jwt.service;

import com.example.jwt.model.comments.Comments;
import com.example.jwt.model.comments.service.CommentsService;
import com.example.jwt.model.post.Post;
import com.example.jwt.model.comments.dto.CommentsDto;
import com.example.jwt.model.comments.repository.CommentsRepository;
import com.example.jwt.model.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Disabled
class CommentsServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private CommentsService commentsService;

    @Test
    void getPostAndComments() throws Exception {
        //given
        Map<String, String> map;

        Long ad = Long.valueOf(1);

        //when
        //map = commentsService.getPostAndComments(ad);

        //then
//        for(String key : map.keySet()) {
//            String value = (String) map.get(key);
//            System.out.println(key + " : " + value);
//        }
    }

    @Test
    void commentsCreate() throws Exception {
        //given
        CommentsDto commentsDto = CommentsDto.builder()
                .commentsContent("민경모 개씹덕이네 ㄹㅇzㅋㅋㅋ")
                .build();

        String forumName = "ㅇㅇㅇㅇ";
        Long postIdx = Long.valueOf(2);

        //when
        Comments comments = commentsService.commentsCreate(forumName, postIdx, commentsDto);

        //then
        assertEquals(commentsDto.getCommentsContent(), comments.getCommentsContent());
    }

    @Test
    void commentsDelete() throws Exception {

        //given
        CommentsDto commentsDto = CommentsDto.builder()
                .commentsContent("내용")
                .build();

        Long ad = Long.valueOf(1);
        Post post = postRepository.findByPostIdx(ad);

        //when
        commentsService.commentsDelete(ad, commentsDto);

        //then
        assertEquals(null, commentsRepository.findByCommentsContentAndPost(commentsDto.getCommentsContent(), post));
    }
}