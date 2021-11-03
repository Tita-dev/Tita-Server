package com.example.jwt.service;

import com.example.jwt.advice.exception.UserNotFoundException;
import com.example.jwt.domain.User;
import com.example.jwt.domain.UserRole;
import com.example.jwt.dto.UserDto;

import java.util.Map;

public interface AuthService {

    final String REDIS_CHANGE_PASSWORD_PREFIX="CPW";

    User signUpUser(UserDto userDto);

    Map<String,String> loginUser(String id, String password) throws Exception;

    void sendVerificationMail(User user) throws UserNotFoundException;

    void verifyEmail(String key) throws UserNotFoundException;

    void modifyUserRole(User user, UserRole userRole);

    User findByUsername(String username) throws UserNotFoundException;

    void isPasswordKeyValidate(String key)throws UserNotFoundException;

    void changePassword(User user, String password) throws UserNotFoundException;

    void requestChangePassword(User user) throws UserNotFoundException;

    boolean checkUsernameDuplicate(String username);

    boolean checkNameDuplicate(String name);
}
