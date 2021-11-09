package com.example.jwt.service;

import com.example.jwt.domain.Forum;
import com.example.jwt.domain.Post;
import com.example.jwt.dto.ForumChangeDto;
import com.example.jwt.dto.ForumDto;
import com.example.jwt.dto.PostChangeDto;
import com.example.jwt.dto.PostDto;
import com.example.jwt.repository.ForumRepository;
import com.example.jwt.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ForumServiceTest {

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ForumService forumService;

    @DisplayName("게시판 목록 구현")
    @Test
    void getForumList() throws Exception{
        //given
        List<String> list;

        //when
        list = forumService.getForumList();

        //than
        for (String i : list){
            System.out.println(i);
        }
    }

    @DisplayName("게시판 생성")
    @Test
    void forumCreate() throws Exception{
        //given
        ForumDto forumDto = ForumDto.builder()
                .forumName("민경ㅇㅇㅇㅇㅇㅇ")
                .explanation("민민민민")
                .build();
        //when
        Forum forum = forumService.forumCreate(forumDto);
        //then
        assertEquals(forumDto.getForumName(),forum.getForumName());
    }

    @DisplayName("게시판 삭제")
    @Test
    void forumDelete() throws Exception{
        //given
        ForumDto forumDto = ForumDto.builder()
                .forumName("민경모모모모모모모")
                .explanation("민민민민")
                .build();
        //when
        forumService.forumDelete(forumDto);

        //then
        assertEquals(false,forumRepository.existsByForumName(forumDto.getForumName()));
    }

    @DisplayName("게시판 수정")
    @Test
    void forumPut() throws Exception{
        //given
        ForumChangeDto forumChangeDto = new ForumChangeDto();
        forumChangeDto.setForumName("민경모민경모");
        forumChangeDto.setNewForumName("민경모씹떢");
        forumChangeDto.setNewExplanation("최윤성 개씹덕");

        //when
        Forum forum = forumService.forumPut(forumChangeDto);

        //then
        assertEquals(forumChangeDto.getNewForumName(),forum.getForumName());
    }

    @Test
    void getForumPostList() throws Exception {
        //given
        List<String> list;

        //when
        list = forumService.getForumPostList("민경모씹떢");

        //then
        for (String i : list){
            System.out.println(i);
        }
    }

    @Test
    void postCreate() throws Exception{
        //given
        PostDto postDto = PostDto.builder()
                .postName("민경모는 에밀리아")
                .content("를 좋아하냐????")
                .build();
        //when
        Post post = forumService.postCreate("민경모모모",postDto);

        //then
        assertEquals(postDto.getPostName(),post.getPostName());
    }

    @Test
    void postDelete() throws Exception{
        //given
        PostDto postDto = PostDto.builder()
                .postName("민경모는 에밀리아")
                .content("를 좋아하냐????")
                .build();
        //when
        forumService.postDelete("민경모모모",postDto);

        //then
        assertEquals(null,postRepository.findByPostNameAndForum(postDto.getPostName(),forumRepository.findByForumName("민경모모모")));
    }

    @Test
    void postPut() throws Exception {
        //given
        PostChangeDto postChangeDto = new PostChangeDto();
        postChangeDto.setPostName("최윤성은 에밀리아");
        postChangeDto.setNewPostName("민경모는 에밀리아");
        postChangeDto.setNewContent("를 좋아하냐????");

        //when
        Post post = forumService.postPut("민경모모모",postChangeDto);

        //then
        assertEquals(postChangeDto.getNewPostName(),post.getPostName());
    }
}