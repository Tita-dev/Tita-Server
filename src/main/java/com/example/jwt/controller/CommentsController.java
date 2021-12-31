package com.example.jwt.controller;

import com.example.jwt.domain.response.CommonResult;
import com.example.jwt.domain.response.ListResult;
import com.example.jwt.domain.response.ResponseService;
import com.example.jwt.domain.response.SingleResult;
import com.example.jwt.dto.CommentsDto;
import com.example.jwt.service.CommentsService;
import com.example.jwt.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tita/forum")
public class CommentsController {

    private final ResponseService responseService;
    private final CommentsService commentsService;

    @GetMapping("/{forumName}/{postIdx}")
    public SingleResult<List<Map<String, String>>> getPostAndComments(@PathVariable Long postIdx) throws Exception {
        List<Map<String, String>> postAndCommentsDto = commentsService.getPostAndComments(postIdx);
        return responseService.getSingleResult(postAndCommentsDto);
    }

    @PostMapping("/{forumName}/{postIdx}/create")
    public CommonResult commentsCreate(@PathVariable String forumName, @PathVariable Long postIdx, @RequestBody CommentsDto commentsDto) throws Exception {
        commentsService.commentsCreate(forumName, postIdx, commentsDto);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/{forumName}/{postIdx}/delete")
    public CommonResult commentsDelete(@PathVariable(name = "postIdx") Long postIdx, @RequestBody CommentsDto commentsDto) throws Exception {
        commentsService.commentsDelete(postIdx, commentsDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("/{forumName}/{postIdx}/like")
    public CommonResult commmentsLike(@PathVariable(name = "postIdx") Long postIdx, @RequestBody CommentsDto commentsDto) throws Exception {
        commentsService.commentsLike(postIdx, commentsDto);
        return responseService.getSuccessResult();
    }
}