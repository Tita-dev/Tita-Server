package com.example.jwt.config.security.auth;

import com.example.jwt.config.security.SecurityConfiguration;
import com.example.jwt.exception.exception.UserNotFoundException;
import com.example.jwt.model.user.SecurityUser;
import com.example.jwt.model.user.User;
import com.example.jwt.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
public class MyUserDetails implements UserDetailsService {

    final private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException());
        return new SecurityUser(user);
    }
}
