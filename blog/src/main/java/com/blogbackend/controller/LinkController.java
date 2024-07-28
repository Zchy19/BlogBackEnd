package com.blogbackend.controller;

import com.blogbackend.domain.ResponseResult;
import com.blogbackend.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/getalllink")
    public ResponseResult getAllLink() {
        return linkService.getAllLink();
    }
}
