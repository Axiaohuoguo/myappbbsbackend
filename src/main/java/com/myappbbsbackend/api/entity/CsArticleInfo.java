package com.myappbbsbackend.api.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cs_article_info
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsArticleInfo implements Serializable {
    /**
     * 文章id
     */
    private Integer id;

    /**
     * 类型id
     */
    private Integer typeid;

    /**
     * 标题
     */
    private String arttitle;

    /**
     * 顶部图片
     */
    private String arthead;

    /**
     * 内容
     */
    private String artcontent;

    /**
     * 作者id
     */
    private Integer userid;

    /**
     * 发布时间
     */
    private Date arttime;

    /**
     * 最后修改时间
     */
    private Date arteditime;

    private static final long serialVersionUID = 1L;
}