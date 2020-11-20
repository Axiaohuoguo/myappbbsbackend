package com.myappbbsbackend.api.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * cs_userinfo
 * @author 
 */
@Data
public class CsUserinfo implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 账号
     */
    private Integer uid;

    /**
     * 	用户名
     */
    private String username;

    /**
     * 密码
     */
//    @JsonIgnore
    private String userpassword;

    /**
     * 用户类型 1-普通用户 ，2-万能墙管理员， 999 - 系统管理员，‘-1’-禁用
     */
    private String usertype;

    /**
     * 邮箱
     */
    private String useremail;

    /**
     * 电话
     */
    private String userphone;

    /**
     * 学校id
     */
    private Integer schoolid;

    /**
     * 个性签名
     */
    private String usersignature;

    /**
     * 头像路径
     */
    private String userheadimg;

    private static final long serialVersionUID = 1L;
}