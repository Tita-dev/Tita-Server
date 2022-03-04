package com.example.jwt.model.user;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Slf4j
@Getter
@Setter
public class SecurityUser extends User {

    private com.example.jwt.model.user.User user;

    public SecurityUser(com.example.jwt.model.user.User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));

        log.info("SecurityUser member.username = {}", user.getUsername());
        log.info("SecurityUser member.password = {}", user.getPassword());
        log.info("SecurityUser member.role = {}", user.getRole().toString());

        this.user = user;
    }
}
