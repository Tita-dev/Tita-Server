package com.example.jwt.controller;

import com.example.jwt.domain.response.CommonResult;
import com.example.jwt.domain.response.ListResult;
import com.example.jwt.domain.response.ResponseService;
import com.example.jwt.dto.CommentsDto;
import com.example.jwt.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tita/forum")
public class CommentsController {

    private final ResponseService responseService;
    private final PostService postService;

    @GetMapping("/{forumName}/{postIdx}")
    public ListResult<String> getPostAndComments (@PathVariable String forumName, @PathVariable Long postIdx) throws Exception{
        /*
            게시글의 내용과 댓글의 내용을 같이 담는 DTO를 만들어 List형태로 return
         */
        return responseService.getListResult(null);
    }

    @PostMapping("/{forumName}/{postIdx}/create")
    public CommonResult commentsCreate (@PathVariable String forumName, @PathVariable Long postIdx ,@RequestBody CommentsDto commentsDto) throws Exception{
        /*
            forumName, postIdx에 해당하는 정보를 매핑하여 Dto에 담아 만든다.
         */
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/{forumName}/{postIdx}/create")
    public CommonResult postDelete(@PathVariable String forumName,@PathVariable Long postIdx ,@RequestBody CommentsDto commentsDto)throws Exception{
        /*

         */
        return responseService.getSuccessResult();
    }
}