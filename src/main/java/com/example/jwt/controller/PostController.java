package com.example.jwt.controller;

import com.example.jwt.domain.response.CommonResult;
import com.example.jwt.domain.response.ListResult;
import com.example.jwt.domain.response.ResponseService;
import com.example.jwt.domain.response.SingleResult;
import com.example.jwt.dto.PostChangeDto;
import com.example.jwt.dto.PostDto;
import com.example.jwt.service.ForumService;
import com.example.jwt.service.PostService;
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
        List<Map<String, String>> postListDto = postService.getForumPostList(forumName);
        return responseService.getSingleResult(postListDto);
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
}
