package com.blogbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blogbackend.domain.entity.User;
import com.blogbackend.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.service.annotation.PutExchange;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserController userController;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void test() {
        SecurityContext context = SecurityContextHolder.getContext();
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken("user", "password", "ROLE_USER");
        Authentication authentication = authenticationManager.authenticate(testingAuthenticationToken);
        context.setAuthentication(testingAuthenticationToken);
        SecurityContextHolder.setContext(context);

    }

    @Autowired
    private UserService userService;

    @Test
    public void test2() {
        String password = "123456";
        String encode = bCryptPasswordEncoder.encode(password);
//        String username = "admin";
//        User user = new User();
//        user.setId(38L);
//        user.setUserName(username);
//        user.setPassword(encode);
//        userService.save(user);
        System.out.println(bCryptPasswordEncoder.matches(password, encode));
    }


}
