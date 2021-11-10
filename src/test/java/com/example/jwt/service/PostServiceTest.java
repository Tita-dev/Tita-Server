package com.example.jwt.service;

import com.example.jwt.domain.Post;
import com.example.jwt.dto.PostChangeDto;
import com.example.jwt.dto.PostDto;
import com.example.jwt.repository.ForumRepository;
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
class PostServiceTest {

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Test
    void getForumPostList() throws Exception {
        //given
        List<String> list;

        //when
        list = postService.getForumPostList("민경모씹떢");

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
        Post post = postService.postCreate("민경모모모",postDto);

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
        postService.postDelete("민경모모모",postDto);

        //then
        assertEquals(null,postRepository.findByPostNameAndForum(postDto.getPostName(),forumRepository.findByForumName("민경모모모")));
    }

    @Test
    void postPut() throws Exception {
        //given
        PostChangeDto postChangeDto = new PostChangeDto();
        postChangeDto.setPostName("민경모는 에밀리아");
        postChangeDto.setNewPostName("최윤성은 에밀리아");
        postChangeDto.setNewContent("를 좋아하냐????");

        //when
        Post post = postService.postPut("민경모모모",postChangeDto);

        //then
        assertEquals(postChangeDto.getNewPostName(),post.getPostName());
    }
}