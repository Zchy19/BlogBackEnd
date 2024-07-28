package com.blogbackend.service;

import com.blogbackend.domain.ResponseResult;
import com.blogbackend.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ZCH
* @description 针对表【sg_link(友链)】的数据库操作Service
* @createDate 2024-07-24 08:46:34
*/
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
