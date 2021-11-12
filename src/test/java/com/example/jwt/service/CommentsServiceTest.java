package com.example.jwt.service;

import com.example.jwt.domain.Comments;
import com.example.jwt.domain.Post;
import com.example.jwt.dto.CommentsDto;
import com.example.jwt.repository.CommentsRepository;
import com.example.jwt.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class CommentsServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private CommentsService commentsService;

    @Test
    void getPostAndComments() throws Exception{
        //given
        List<String> list;

        Long ad = Long.valueOf(1);

        //when
        list = commentsService.getPostAndComments(ad);

        //then
        for (String i : list) {
            System.out.println(i);
        }
    }

    @Test
    void commentsCreate() throws Exception{
        //given
        CommentsDto commentsDto = CommentsDto.builder()
                .comments("민경모 개씹덕이네 ㄹㅇㅋㅋ")
                .build();

        Long ad = Long.valueOf(1);

        //when
        Comments comments = commentsService.commentsCreate(ad,commentsDto);

        //then
        assertEquals(commentsDto.getComments(),comments.getComments());
    }

    @Test
    void commentsDelete() throws Exception{

        //given
        CommentsDto commentsDto = CommentsDto.builder()
                .comments("민경모 개씹덕이네 ㄹㅇㅋㅋ")
                .build();

        Long ad = Long.valueOf(1);
        Post post = postRepository.findByPostIdx(ad);

        //when
        commentsService.commentsDelete(ad,commentsDto);

        //then
        assertEquals(null,commentsRepository.findByCommentsAndPost(commentsDto.getComments(),post));
    }
}