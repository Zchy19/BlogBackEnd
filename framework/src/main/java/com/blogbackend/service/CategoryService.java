package com.blogbackend.service;

import com.blogbackend.domain.ResponseResult;
import com.blogbackend.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ZCH
* @description 针对表【sg_category(分类表)】的数据库操作Service
* @createDate 2024-07-23 14:07:28
*/
public interface CategoryService extends IService<Category> {

    ResponseResult getCategorList();


}
