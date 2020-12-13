package com.myappbbsbackend.api.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cs_glsqb
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsGlsqb implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 申请内容 20 字
     */
    private String content;
    /**
     * 状态 申请状态 初始 -1 通过 -2 驳回 -3
     */
    private int glstate ;

    private static final long serialVersionUID = 1L;
}