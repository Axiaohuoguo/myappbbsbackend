package com.myappbbsbackend.api.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cs_reply_info
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsReplyInfo implements Serializable {
    /**
     * 回复id
     */
    private Integer id;

    /**
     * 文章id
     */
    private Integer artid;

    /**
     * 回复内容
     */
    private String replycontent;

    /**
     * 回复时间
     */
    private Date replytime;

    /**
     * 回复人id
     */
    private Integer replyuserid;

    private static final long serialVersionUID = 1L;
}