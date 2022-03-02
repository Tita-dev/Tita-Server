package com.example.jwt.util;

import com.example.jwt.exception.exception.UserNotFoundException;
import com.example.jwt.model.user.User;
import com.example.jwt.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserUtil {

    private final UserRepository userRepository;

    public static String getCurrentUsername(){
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    public User getCurrentUser(){
        return userRepository.findByUsername(CurrentUserUtil.getCurrentUsername()).orElseThrow(()-> new UserNotFoundException());
    }
}
