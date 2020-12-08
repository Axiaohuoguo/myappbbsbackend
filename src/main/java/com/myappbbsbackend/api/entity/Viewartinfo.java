package com.myappbbsbackend.api.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * viewartinfo
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viewartinfo implements Serializable {
    private Integer id;

    private int typeid ;

    private String arttitle;

    private String arthead;

    private String artcontent;

    private Date arttime;

    private Date arteditime;

    private Integer userid;

    private String username;

    private Integer schoolid;

    private String userheadimg;

    private String shoolname;

    private int replynum;

    private int likenum;

    private static final long serialVersionUID = 1L;
}