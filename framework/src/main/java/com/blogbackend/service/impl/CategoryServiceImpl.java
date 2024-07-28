package com.blogbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blogbackend.constants.SystemConstants;
import com.blogbackend.domain.ResponseResult;
import com.blogbackend.domain.entity.Article;
import com.blogbackend.domain.entity.Category;
import com.blogbackend.domain.vo.CategoryVo;
import com.blogbackend.service.ArticleService;
import com.blogbackend.service.CategoryService;
import com.blogbackend.mapper.CategoryMapper;
import com.blogbackend.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
* @author ZCH
* @description 针对表【sg_category(分类表)】的数据库操作Service实现
* @createDate 2024-07-23 14:07:28
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{
    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategorList() {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> List = articleService.list(lambdaQueryWrapper);
        Set<Long> categorySet = List.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        java.util.List<Category> categoryList = listByIds(categorySet);
        java.util.List<Category> categoriesNormal = categoryList.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        java.util.List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categoriesNormal, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }


}




