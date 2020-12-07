package com.myappbbsbackend.api.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cs_art_like_info
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsArtLikeInfo implements Serializable {
    /**
     * 点赞id
     */
    private Integer id;

    /**
     * 文章id
     */
    private Integer artid;

    /**
     * 点赞文章的人的id
     */
    private Integer likeuserid;

    private static final long serialVersionUID = 1L;
}