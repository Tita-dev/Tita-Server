package com.example.jwt.controller;

import com.example.jwt.domain.response.CommonResult;
import com.example.jwt.domain.response.ListResult;
import com.example.jwt.domain.response.ResponseService;
import com.example.jwt.dto.ForumChangeDto;
import com.example.jwt.dto.ForumDto;
import com.example.jwt.dto.PostDto;
import com.example.jwt.service.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tita/forum")
@RequiredArgsConstructor
public class ForumController {

    private final ResponseService responseService;
    private final ForumService forumService;

    @GetMapping("/list")
    public ListResult<String> forumList()throws Exception {
        List<String> forumDtoList = forumService.getForumList();
        return responseService.getListResult(forumDtoList);
    }

    @PostMapping("/create")
    public CommonResult forumCreate(@RequestBody ForumDto forumDto) throws Exception{
        forumService.forumCreate(forumDto);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/delete")
    public CommonResult forumDelete(@RequestBody ForumDto forumDto)throws Exception{
        forumService.forumDelete(forumDto);
        return responseService.getSuccessResult();
    }

    @PutMapping("/put")
    public CommonResult forumPut(@RequestBody ForumChangeDto forumChangeDto) throws Exception{
        forumService.forumPut(forumChangeDto);
        return responseService.getSuccessResult();
    }

    @GetMapping("/{forumName}/list")
    public List<String> postList()throws Exception{

        return null;
    }

    @PostMapping("/{forumName}/create")
    public CommonResult postCreate(@PathVariable String forumName, @RequestBody PostDto postDto) throws Exception{

        return responseService.getSuccessResult();
    }

    @DeleteMapping("/{forumName}/delete")
    public CommonResult postDelete()throws Exception{
        return responseService.getSuccessResult();
    }

    @PutMapping("/{forumName}/put")
    public CommonResult postPut()throws Exception{
        return responseService.getSuccessResult();
    }
}
