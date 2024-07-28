package com.blogbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blogbackend.constants.SystemConstants;
import com.blogbackend.domain.ResponseResult;
import com.blogbackend.domain.entity.Article;
import com.blogbackend.domain.entity.Category;
import com.blogbackend.domain.vo.ArticleDetailVo;
import com.blogbackend.domain.vo.ArticleListVo;
import com.blogbackend.domain.vo.HotArticleVo;
import com.blogbackend.domain.vo.PageVo;
import com.blogbackend.mapper.ArticleMapper;
import com.blogbackend.service.ArticleService;
import com.blogbackend.service.CategoryService;
import com.blogbackend.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    @Lazy
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();

        //bean拷贝
        HotArticleVo hotArticleVo = new HotArticleVo();
        List<? extends HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articles, hotArticleVo.getClass());
        return ResponseResult.okResult(hotArticleVos);

    }

    @Override
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId), Article::getCategoryId, categoryId)
                .eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getIsTop);
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page,lambdaQueryWrapper);
        List<Article> articleList = page.getRecords();
        //获得categoryName
        List<Object> collect = articleList.stream()
                .peek(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

        //vo封装
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(collect, ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        Article article = getById(id);
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copeBean(article, ArticleDetailVo.class);
        Category categoryServiceById = categoryService.getById(article.getCategoryId());
        if (categoryServiceById != null) {
            articleDetailVo.setCategoryName(categoryServiceById.getName());
        }
        return ResponseResult.okResult(articleDetailVo);
    }
}
