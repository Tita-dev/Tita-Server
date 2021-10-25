package com.example.jwt.controller;

import com.example.jwt.domain.response.CommonResult;
import com.example.jwt.domain.response.ListResult;
import com.example.jwt.domain.response.ResponseService;
import com.example.jwt.dto.ForumChangeDto;
import com.example.jwt.dto.ForumDto;
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
    public ListResult<ForumDto> forumList()throws Exception {
        List<ForumDto> forumDtoList = forumService.getForumList();
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
        /*
            Forum Service 제작 후 forumChangeDto에서 기존 forumname, 새로운 forumname, explanation 을 가져와 해당하는 forum의 정보 변경
                +예외 처리
  리      */
        return responseService.getSuccessResult();
    }
}
