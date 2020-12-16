package com.myappbbsbackend.api.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * viewrereply
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viewrereply implements Serializable {
    private Integer id;

    private Integer replyid;

    private String rereplycontent;

    private Date rereplytime;

    private Integer rereplyuserid;

    private String username;

    private String userheadimg;

    private static final long serialVersionUID = 1L;
}