package com.blogbackend.controller;

import com.blogbackend.domain.ResponseResult;
import com.blogbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/front/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/commentList")
    public ResponseResult getCommentList(Integer pageNum,Integer pageSize,Long categoryId,Long Type) {
        return commentService.getCommentList(pageNum,pageSize,categoryId,Type);
    }
}
