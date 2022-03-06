package com.example.jwt.model.post.controller;

import com.example.jwt.response.result.CommonResult;
import com.example.jwt.response.ResponseService;
import com.example.jwt.response.result.SingleResult;
import com.example.jwt.model.post.dto.PostChangeDto;
import com.example.jwt.model.post.dto.PostDto;
import com.example.jwt.model.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tita/forum")
public class PostController {

    private final ResponseService responseService;
    private final PostService postService;

    @GetMapping("/{forumName}/list")
    public SingleResult<List<Map<String, String>>> postList(@PathVariable String forumName) throws Exception {
        return responseService.getSingleResult(postService.getForumPostList(forumName));
    }

    @PostMapping("/{forumName}/create")
    public CommonResult postCreate(@PathVariable String forumName, @RequestBody PostDto postDto) throws Exception {
        postService.postCreate(forumName, postDto);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/{forumName}/delete")
    public CommonResult postDelete(@PathVariable String forumName, @RequestBody PostDto postDto) throws Exception {
        postService.postDelete(forumName, postDto);
        return responseService.getSuccessResult();
    }

    @PutMapping("/{forumName}/put")
    public CommonResult postPut(@PathVariable String forumName, @RequestBody PostChangeDto postChangeDto) throws Exception {
        postService.postPut(forumName, postChangeDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("/{forumName}/like")
    public CommonResult postLike(@PathVariable String forumName, @RequestBody PostDto postDto) throws Exception {
        postService.postLike(forumName, postDto);
        return responseService.getSuccessResult();
    }

    @GetMapping("/best")
    public SingleResult<List<Map<String, String>>> bestPost() {
        return responseService.getSingleResult(postService.getBestPost());
    }

    @GetMapping("/notice")
    public SingleResult<List<Map<String, String>>> noticePost() {
        return responseService.getSingleResult(postService.getNoticePost());
    }
}
