package com.example.d.demo.security;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {

@Autowired PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        //TODO 从数据库读取你的用户对象
        String password = passwordEncoder.encode("123456");
        log.info("Authenticating {} , {}", login,password);
        return createSpringSecurityUser(login,password);
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String login,String password) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("human"));
        return new org.springframework.security.core.userdetails.User(login, password, grantedAuthorities);
    }
}