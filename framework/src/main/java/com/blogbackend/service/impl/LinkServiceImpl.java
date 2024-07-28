package com.blogbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blogbackend.constants.SystemConstants;
import com.blogbackend.domain.ResponseResult;
import com.blogbackend.domain.entity.Link;
import com.blogbackend.domain.vo.AllLinkVo;
import com.blogbackend.service.LinkService;
import com.blogbackend.mapper.LinkMapper;
import com.blogbackend.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ZCH
* @description 针对表【sg_link(友链)】的数据库操作Service实现
* @createDate 2024-07-24 08:46:34
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
    implements LinkService{

    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> linkList = list(lambdaQueryWrapper);
        List<AllLinkVo> allLinkVos = BeanCopyUtils.copyBeanList(linkList, AllLinkVo.class);
        return ResponseResult.okResult(allLinkVos);
    }
}




