package com.example.jwt.config.security;

import com.example.jwt.config.security.auth.MyUserDetails;
import com.example.jwt.config.security.jwt.JwtRequestFilter;
import com.example.jwt.config.security.jwt.JwtTokenFilterConfigurer;
import com.example.jwt.config.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity //(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MyUserDetails myUserDetails;

    private final JwtUtil jwtUtil;

    @Override
    protected void configure(HttpSecurity  http) throws Exception{
        http
                .cors().and()
                .csrf().disable()
                .httpBasic().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/tita/forum/**").hasAuthority("ROLE_STUDENT")
                //.hasAnyRole("ROLE_STUDENT","ROLE_DORMITORY_MANAGER","ROLE_SCHOOL_MANAGER","ROLE_SCHOOL_ADMIN")
                .antMatchers("/tita/admin/authorization/**").hasAuthority("ROLE_SCHOOL_ADMIN")
                .antMatchers("/tita/**").permitAll()
                .anyRequest().authenticated();
        http.userDetailsService(myUserDetails);

        http.apply(new JwtTokenFilterConfigurer(jwtUtil));
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/h2-console/**", "/exception/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/swagger/**")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/api-docs")
                .antMatchers("/configuration/ui")
                .antMatchers("/configuration/security")
                .antMatchers("/context/**");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManagerBean();
    }
}