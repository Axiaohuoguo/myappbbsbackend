package com.myappbbsbackend.api.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cs_re_reply_info
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsReReplyInfo implements Serializable {
    /**
     * 回复的回复的id
     */
    private Integer id;

    /**
     * 回复的id(回复的是哪一条回复)
     */
    private Integer replyid;

    /**
     * 回复 回复的内容
     */
    private String rereplycontent;

    /**
     * 回复 回复的时间
     */
    private Date rereplytime;

    /**
     * 回复回复的人的id
     */
    private Integer rereplyuserid;

    private static final long serialVersionUID = 1L;
}