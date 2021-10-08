package com.example.jwt.controller;

import com.example.jwt.domain.response.CommonResult;
import com.example.jwt.domain.response.ListResult;
import com.example.jwt.domain.response.ResponseService;
import com.example.jwt.dto.ForumChangeDto;
import com.example.jwt.dto.ForumDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tita/forum")
@RequiredArgsConstructor
public class ForumController {

    private final ResponseService responseService;

    @GetMapping("/list")
    public ListResult<String> forumList()throws Exception {
        /*
            Forum Service 제작 후 forumList 불러와 전달
                + 예외처리
         */
        return responseService.getListResult(null);
    }

    @PostMapping("/create")
    public CommonResult forumCreate(@RequestBody ForumDto forumDto) throws Exception{
        /*
            Forum Service 제작 후 forumDto에 담겨있는 정보 토대로 forum 제작
                + 예외처리
         */
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/delete")
    public CommonResult forumDelete(@RequestBody ForumDto forumDto)throws Exception{
        /*
            Forum Service 제작 후 forumDto에서 forumname을 가져와 해당하는 forum삭제
                + 예외처리
         */
        return responseService.getSuccessResult();
    }

    @PutMapping("/put")
    public CommonResult forumPut(@RequestBody ForumChangeDto forumChangeDto) throws Exception{
        /*
            Forum Service 제작 후 forumChangeDto에서 기존 forumname, 새로운 forumname, explanation 을 가져와 해당하는 forum의 정보 변경
                +예외 처리
         */
        return responseService.getSuccessResult()
    }
}
