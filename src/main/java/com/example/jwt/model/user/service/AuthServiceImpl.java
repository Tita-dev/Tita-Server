package com.example.jwt.model.user.service;

import com.example.jwt.config.security.redis.KeyConfig;
import com.example.jwt.config.security.redis.RedisConfig;
import com.example.jwt.config.security.auth.CurrentUser;
import com.example.jwt.config.security.jwt.JwtUtil;
import com.example.jwt.exception.exception.*;
import com.example.jwt.model.user.enum_type.UserRole;
import com.example.jwt.model.user.dto.UserDto;
import com.example.jwt.model.user.service.email.EmailService;
import com.example.jwt.model.user.User;
import com.example.jwt.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RedisConfig redisConfig;
    private final KeyConfig keyConfig;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;
    private final EmailService emailService;

    @Override
    public User signUpUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UserAlreadyExistsException();
        }
        if (userRepository.findByName(userDto.getName()) != null) {
            throw new UserNicknameOverlapException();
        }
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new UserEmailOverlapException();
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(userDto.toEntity());
    }

    @Override
    public Map<String, String> loginUser(String id, String password) {
        User user = userRepository.findByUsername(id).orElseThrow(()-> new UserNotFoundException());
        if (!passwordEncoder.matches(password,user.getPassword())) throw new UserNotFoundException();

        String accessToken = jwtUtil.generateToken(user.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        redisConfig.setDataExpire(user.getUsername(), refreshToken, JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);


        Map<String, String> map = Map.of("username",user.getUsername(),"ROLE",String.valueOf(user.getRole()),"accessToken",accessToken,"refreshToken",refreshToken);
        return map;
    }

    @Override
    public void logout(){
        redisConfig.deleteData(currentUser.getCurrentUser().getUsername());
    }

    @Override
    public void sendVerificationMail(User user) throws UserNotFoundException {
        if (user == null) throw new UserNotFoundException();
        String authKey = keyConfig.getKey(6);
        redisConfig.setDataExpire(authKey, user.getUsername(), 60 * 30L);
        emailService.send(user.getEmail(), "[Tita] 회원가입 인증 이메일 입니다.", "인증번호는 " + authKey);
    }

    @Override
    public void verifyEmail(String key) throws UserNotFoundException {
        String memberId = redisConfig.getData(key);
        User user = userRepository.findByUsername(memberId).orElseThrow(()-> new InvalidAuthenticationNumberException());
        modifyUserRole(user, UserRole.ROLE_STUDENT);
        redisConfig.deleteData(key);
    }

    @Override
    public void modifyUserRole(User user, UserRole userRole) {
        user.setRole(userRole);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException());
        return user;
    }

    @Override
    public void requestChangePassword(User user) throws UserNotFoundException {
        String authKey = keyConfig.getKey(6);
        if (user == null) throw new UserNotFoundException();
        redisConfig.setDataExpire(authKey, user.getUsername(), 60 * 30L);
        emailService.send(user.getEmail(), "[Tita] 사용자 비밀번호 변경 메일입니다.", "인증번호는 " + authKey);
    }

    @Override
    public void isPasswordKeyValidate(String key) {
        String userName = redisConfig.getData(key);
        User user = userRepository.findByUsername(userName).orElseThrow(()-> new InvalidAuthenticationNumberException());
        modifyUserRole(user, UserRole.ROLE_PASSWORD_CHANGE);
    }

    @Override
    public void changePassword(User user, String password) throws UserNotFoundException {
        if (user == null) throw new UserNotFoundException();
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(UserRole.ROLE_STUDENT);
        userRepository.save(user);
    }

    @Override
    public void requestFindUsername(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UserNotFoundException();
        String authKey = keyConfig.getKey(6);
        redisConfig.setDataExpire(authKey, user.getUsername(), 60 * 30L);
        emailService.send(user.getEmail(), "[Tita] 사용자 아이디 확인 메일입니다.", "인증번호는 " + authKey);
    }

    @Override
    public String responseFindUsername(String key) throws Exception {
        String userName = redisConfig.getData(key);
        User user = userRepository.findByUsername(userName).orElseThrow(()-> new InvalidAuthenticationNumberException());
        return user.getUsername();
    }

    @Override
    public boolean checkUsernameDuplicate(String username) throws Exception{
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean checkNameDuplicate(String name) throws Exception{
        return userRepository.existsByName(name);
    }

}
