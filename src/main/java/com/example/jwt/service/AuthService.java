package com.example.jwt.service;

import com.example.jwt.advice.exception.UserLoginFailedException;
import com.example.jwt.advice.exception.UserNotFoundException;
import com.example.jwt.domain.User;
import com.example.jwt.domain.UserRole;
import com.example.jwt.dto.LogoutDto;
import com.example.jwt.dto.UserDto;

import java.util.Map;

public interface AuthService {

    final String REDIS_CHANGE_PASSWORD_PREFIX = "CPW";

    User signUpUser(UserDto userDto);

    Map<String, String> loginUser(String id, String password) throws UserLoginFailedException;

    void logout();

    void sendVerificationMail(User user) throws UserNotFoundException; //email send Exception

    void verifyEmail(String key) throws UserNotFoundException; //email verifyException

    void modifyUserRole(User user, UserRole userRole); //UserRoleModify Exception

    User findByUsername(String username) throws UserNotFoundException;

    void requestChangePassword(User user) throws UserNotFoundException;

    void isPasswordKeyValidate(String key) throws UserNotFoundException;

    void changePassword(User user, String password) throws UserNotFoundException;

    void requestFindUsername(String email) throws Exception;

    String responseFindUsername(String key) throws Exception;

    boolean checkUsernameDuplicate(String username) throws Exception;

    boolean checkNameDuplicate(String name) throws Exception;
}
