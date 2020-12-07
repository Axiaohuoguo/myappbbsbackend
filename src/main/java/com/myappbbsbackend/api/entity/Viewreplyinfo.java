package com.myappbbsbackend.api.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * viewreplyinfo
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viewreplyinfo implements Serializable {
    private Integer id;

    private Integer artid;

    private String replycontent;

    private Date replytime;

    private Integer replyuserid;

    private String username;

    private String userheadimg;

    private static final long serialVersionUID = 1L;
}