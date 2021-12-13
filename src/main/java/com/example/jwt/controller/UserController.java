package com.example.jwt.controller;

import com.example.jwt.domain.response.CommonResult;
import com.example.jwt.domain.response.ResponseService;
import com.example.jwt.domain.response.SingleResult;
import com.example.jwt.dto.*;
import com.example.jwt.util.JwtUtil;
import com.example.jwt.util.RedisUtil;
import com.example.jwt.domain.User;
import com.example.jwt.service.AuthService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tita/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    private final ResponseService responseService;

    @PostMapping("/signup")
    public CommonResult signUpUser(@RequestBody UserDto userDto) throws Exception {
        authService.signUpUser(userDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("/login")
    public SingleResult<Map<String, String>> login(@RequestBody UserSigninDto userSigninDto) throws Exception {
        return responseService.getSingleResult(authService.loginUser(userSigninDto.getUsername(), userSigninDto.getPassword()));
    }

    @PostMapping("/verify")
    public CommonResult verify(@RequestBody RequestVerifyEmailDto requestVerifyEmailDto) throws NotFoundException {
        authService.sendVerificationMail(authService.findByUsername(requestVerifyEmailDto.getUsername())); //이메일 안넣으면 오류
        return responseService.getSuccessResult();
    }

    @GetMapping("/verify/key")
    public CommonResult getVerify(@RequestBody String key) {
        authService.verifyEmail(key);
        return responseService.getSuccessResult();
    }

    @PostMapping("/password")
    public CommonResult requestChangePassword(@RequestBody RequestChangePasswordEmailDto requestChangePasswordEmailDto) throws NoSuchFieldException {
        User user = authService.findByUsername(requestChangePasswordEmailDto.getUsername());
        if (!user.getEmail().equals(requestChangePasswordEmailDto.getEmail())) throw new NoSuchFieldException();
        authService.requestChangePassword(user);
        return responseService.getSuccessResult();
    }

    @GetMapping("/password/key")
    public CommonResult isPasswordKeyValidate(@RequestBody String key) {
        authService.isPasswordKeyValidate(key);
        return responseService.getSuccessResult();
    }

    @PutMapping("/password")
    public CommonResult changePassword(@RequestBody RequestChangePasswordDto requestChangePasswordDto) {
        authService.changePassword(authService.findByUsername(requestChangePasswordDto.getUsername()), requestChangePasswordDto.getPassword());
        return responseService.getSuccessResult();
    }

    @PostMapping("/username")
    public CommonResult requestFindUsername(@RequestBody String email) throws Exception {
        authService.requestFindUsername(email);
        return responseService.getSuccessResult();
    }

    @GetMapping("/username/key")
    public SingleResult<String> responseFindUsername(@RequestBody String key) throws Exception {
        return responseService.getSingleResult(authService.responseFindUsername(key));
    }

    @GetMapping("/username/{username}/exists")
    public SingleResult<Boolean> checkUsernameDuplicate(@PathVariable String username) throws Exception{
        return responseService.getSingleResult(authService.checkUsernameDuplicate(username));
    }

    @GetMapping("/name/{name}/exists")
    public SingleResult<Boolean> checkNameDuplicate(@PathVariable String name) throws Exception{
        return responseService.getSingleResult(authService.checkNameDuplicate(name));
    }
}
