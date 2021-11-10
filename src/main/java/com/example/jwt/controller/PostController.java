package com.example.jwt.controller;

import com.example.jwt.domain.response.CommonResult;
import com.example.jwt.domain.response.ListResult;
import com.example.jwt.domain.response.ResponseService;
import com.example.jwt.dto.PostChangeDto;
import com.example.jwt.dto.PostDto;
import com.example.jwt.service.ForumService;
import com.example.jwt.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tita/forum")
public class PostController {

    private final ResponseService responseService;
    private final PostService postService;

    @GetMapping("/{forumName}/list")
    public ListResult<String> postList(@PathVariable String forumName)throws Exception{
        List<String> postListDto = postService.getForumPostList(forumName);
        return responseService.getListResult(postListDto);
    }

    @PostMapping("/{forumName}/create")
    public CommonResult postCreate(@PathVariable String forumName, @RequestBody PostDto postDto) throws Exception{
        postService.postCreate(forumName,postDto);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/{forumName}/delete")
    public CommonResult postDelete(@PathVariable String forumName, @RequestBody PostDto postDto)throws Exception{
        postService.postDelete(forumName,postDto);
        return responseService.getSuccessResult();
    }

    @PutMapping("/{forumName}/put")
    public CommonResult postPutPathVariable (String forumName, @RequestBody PostChangeDto postChangeDto)throws Exception{
        postService.postPut(forumName,postChangeDto);
        return responseService.getSuccessResult();
    }
}
