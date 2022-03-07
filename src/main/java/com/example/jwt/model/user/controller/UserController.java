package com.example.jwt.model.user.controller;

import com.example.jwt.model.user.dto.*;
import com.example.jwt.response.result.CommonResult;
import com.example.jwt.response.ResponseService;
import com.example.jwt.response.result.SingleResult;
import com.example.jwt.model.user.User;
import com.example.jwt.model.user.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tita/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;

    @PostMapping("/signup")
    public CommonResult signUpUser(@RequestBody UserDto userDto) throws Exception {
        userService.signUpUser(userDto);
        return responseService.getSuccessResult();
    }


    @PostMapping("/login")
    public SingleResult<Map<String, String>> login(@RequestBody UserSigninDto userSigninDto) throws Exception {
        return responseService.getSingleResult(userService.loginUser(userSigninDto.getUsername(), userSigninDto.getPassword()));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping("/logout")
    public CommonResult logout() throws Exception{
        userService.logout();
        return responseService.getSuccessResult();
    }

    @PostMapping("/verify")
    public CommonResult verify(@RequestBody RequestVerifyEmailDto requestVerifyEmailDto) throws NotFoundException {
        userService.sendVerificationMail(userService.findByUsername(requestVerifyEmailDto.getUsername())); //이메일 안넣으면 오류
        return responseService.getSuccessResult();
    }

    @GetMapping("/verify/key")
    public CommonResult getVerify(@RequestBody String key) {
        userService.verifyEmail(key);
        return responseService.getSuccessResult();
    }

    @PostMapping("/password")
    public CommonResult requestChangePassword(@RequestBody RequestChangePasswordEmailDto requestChangePasswordEmailDto) throws NoSuchFieldException {
        User user = userService.findByUsername(requestChangePasswordEmailDto.getUsername());
        if (!user.getEmail().equals(requestChangePasswordEmailDto.getEmail())) throw new NoSuchFieldException();
        userService.requestChangePassword(user);
        return responseService.getSuccessResult();
    }

    @GetMapping("/password/key")
    public CommonResult isPasswordKeyValidate(@RequestBody String key) {
        userService.isPasswordKeyValidate(key);
        return responseService.getSuccessResult();
    }

    @PutMapping("/password")
    public CommonResult changePassword(@RequestBody RequestChangePasswordDto requestChangePasswordDto) {
        userService.changePassword(userService.findByUsername(requestChangePasswordDto.getUsername()), requestChangePasswordDto.getPassword());
        return responseService.getSuccessResult();
    }

    @PostMapping("/username")
    public CommonResult requestFindUsername(@RequestBody String email) throws Exception {
        userService.requestFindUsername(email);
        return responseService.getSuccessResult();
    }

    @GetMapping("/username/key")
    public SingleResult<String> responseFindUsername(@RequestBody String key) throws Exception {
        return responseService.getSingleResult(userService.responseFindUsername(key));
    }

    @GetMapping("/username/{username}/exists")
    public SingleResult<Boolean> checkUsernameDuplicate(@PathVariable String username) throws Exception{
        return responseService.getSingleResult(userService.checkUsernameDuplicate(username));
    }

    @GetMapping("/name/{name}/exists")
    public SingleResult<Boolean> checkNameDuplicate(@PathVariable String name) throws Exception{
        return responseService.getSingleResult(userService.checkNameDuplicate(name));
    }
}
