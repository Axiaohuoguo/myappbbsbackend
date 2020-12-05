package com.myappbbsbackend.api.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cs_article_type
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsArticleType implements Serializable {
    /**
     * 类型id
     */
    private Integer id;

    /**
     * 动态类型名称
     */
    private String artname;

    /**
     * 	状态
     */
    private String artstate;

    /**
     * 类别图片
     */
    private String artimgurl;

    private static final long serialVersionUID = 1L;
}