package com.example.jwt.service;

import com.example.jwt.advice.exception.UserAlreadyExistsException;
import com.example.jwt.advice.exception.UserNotFoundException;
import com.example.jwt.domain.UserRole;
import com.example.jwt.dto.MemberDto;
import com.example.jwt.util.*;
import com.example.jwt.domain.User;
import com.example.jwt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthServiceMpl implements AuthService{

    private final MemberRepository memberRepository;
    private final RedisUtil redisUtil;
    private final EmailService emailService;
    private final KeyUtil keyUtil;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUpUser(MemberDto memberDto) {
        if (memberRepository.findByUsername(memberDto.getUsername()) != null) {
            throw new UserAlreadyExistsException();
        }
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberRepository.save(memberDto.toEntity());
    }

    @Override
    public Map<String,String> loginUser(String id, String password){
        User user = memberRepository.findByUsername(id);
        if (user == null) throw new UserNotFoundException();
        boolean passwordCheak = passwordEncoder.matches(password,user.getPassword());
        if(!passwordCheak) throw new UserNotFoundException();
        final String accessToken = jwtUtil.generateToken(user.getUsername());
        final String refreshJwt = jwtUtil.generateRefreshToken(user.getUsername());
        redisUtil.setDataExpire(refreshJwt, user.getUsername(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
        Map<String ,String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("acessToken",accessToken);
        map.put("refreshToken", refreshJwt);
        return map;
    }

    @Override
    public void sendVerificationMail(User user) throws UserNotFoundException {
        if(user ==null) throw new UserNotFoundException();
        String authkey = keyUtil.getKey(6);
        redisUtil.setDataExpire(authkey, user.getUsername(),60 * 30L);
        emailService.sendMail(user.getEmail(),"[Tita] 회원가입 인증 이메일 입니다.","인증번호는 "+authkey);

    }

    @Override
    public void verifyEmail(String key) throws UserNotFoundException {
        String memberId = redisUtil.getData(key);
        User user = memberRepository.findByUsername(memberId);
        if (user ==null) throw new UserNotFoundException();
        modifyUserRole(user,UserRole.ROLE_USER);
        redisUtil.deleteData(key);
    }

    @Override
    public void modifyUserRole(User user, UserRole userRole) {
        user.setRole(userRole);
        memberRepository.save(user);
    }

    @Override
    public User findByUsername(String username) throws UserNotFoundException {
        User user = memberRepository.findByUsername(username);
        if (user ==null) throw new UserNotFoundException();
        return user;
    }

    @Override
    public void requestChangePassword(User user) throws UserNotFoundException{
        String authkey = keyUtil.getKey(6);
        if(user == null) throw new UserNotFoundException();
        redisUtil.setDataExpire(authkey, user.getUsername(),60 * 30L);
        emailService.sendMail(user.getEmail(),"[Tita] 사용자 비밀번호 변경 메일입니다.","인증번호는 "+ authkey);
    }

    @Override
    public void isPasswordKeyValidate(String key){
        String memberId = redisUtil.getData(key);
        User user = memberRepository.findByUsername(memberId);
        if (user ==null) throw new UserNotFoundException();
        modifyUserRole(user,UserRole.ROLE_PASSWORD_CHANGE);
    }

    @Override
    public void changePassword(User user, String password) throws UserNotFoundException{
        if(user == null) throw new UserNotFoundException();
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(UserRole.ROLE_USER);
        memberRepository.save(user);
    }

    @Override
    public boolean checkUsernameDuplicate(String username) {
        return memberRepository.existsByUsername(username);
    }

    @Override
    public boolean checkNameDuplicate(String name) {
        return memberRepository.existsByName(name);
    }

}
