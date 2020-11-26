package com.myappbbsbackend.api.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * viewuserinfo
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viewuserinfo implements Serializable {
    private Integer id;

    private Integer uid;

    private String useremail;

    private String username;

    private String userheadimg;

    private String userphone;

    private String usersignature;

    private String usertype;

    private String shoolname;

    private String schoolid;

    private static final long serialVersionUID = 1L;

}