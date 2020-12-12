package com.myappbbsbackend.api.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * viewfserfollowinfo
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viewfserfollowinfo implements Serializable {
    private Integer id;

    private Integer myuserid;

    private Integer fansid;

    private String username;

    private String userheadimg;

    private static final long serialVersionUID = 1L;
}