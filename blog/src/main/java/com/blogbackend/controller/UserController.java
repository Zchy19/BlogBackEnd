package com.blogbackend.controller;


import com.blogbackend.domain.ResponseResult;
import com.blogbackend.domain.entity.User;
import com.blogbackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZCH
 */
@RestController
@RequestMapping("/front")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return userService.blogLogin(user);
    }
}
