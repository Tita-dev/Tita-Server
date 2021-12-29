package com.example.jwt.model.forum.controller;

import com.example.jwt.model.forum.service.ForumService;
import com.example.jwt.response.result.CommonResult;
import com.example.jwt.response.result.ListResult;
import com.example.jwt.response.ResponseService;
import com.example.jwt.model.forum.dto.ForumChangeDto;
import com.example.jwt.model.forum.dto.ForumDto;
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
    public ListResult<String> forumList() throws Exception {
        List<String> forumDtoList = forumService.getForumList();
        return responseService.getListResult(forumDtoList);
    }

    @PostMapping("/create")
    public CommonResult forumCreate(@RequestBody ForumDto forumDto) throws Exception {
        forumService.forumCreate(forumDto);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/delete")
    public CommonResult forumDelete(@RequestBody ForumDto forumDto) throws Exception {
        forumService.forumDelete(forumDto);
        return responseService.getSuccessResult();
    }

    @PutMapping("/put")
    public CommonResult forumPut(@RequestBody ForumChangeDto forumChangeDto) throws Exception {
        forumService.forumPut(forumChangeDto);
        return responseService.getSuccessResult();
    }
}
