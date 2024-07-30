package com.blogbackend.domain.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class CommentVo {
    private Long id;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 根评论id
     */
    private Long rootId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 所回复的目标评论的userid
     */
    private Long toCommentUserId;

    /**
     * 回复目标评论id
     */
    private Long toCommentId;

    private String toCommentUserName;

    /**
     *
     */
    private Long createBy;

    /**
     *
     */
    private Date createTime;

    private String username;

//    /**
//     *
//     */
//    private Long updateBy;
//
//    /**
//     *
//     */
//    private Date updateTime;
}
