package com.myappbbsbackend.api.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cs_follow_info
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsFollowInfo implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer myuserid;

    /**
     * 粉丝id
     */
    private Integer fansid;

    private static final long serialVersionUID = 1L;
}