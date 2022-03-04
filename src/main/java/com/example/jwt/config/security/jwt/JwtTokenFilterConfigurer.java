package com.example.jwt.config.security.jwt;

import com.example.jwt.config.security.auth.MyUserDetails;
import com.example.jwt.util.RedisUtil;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    final private JwtUtil jwtUtil;

    public JwtTokenFilterConfigurer(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        JwtRequestFilter customFilter = new JwtRequestFilter(jwtUtil);
        httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
