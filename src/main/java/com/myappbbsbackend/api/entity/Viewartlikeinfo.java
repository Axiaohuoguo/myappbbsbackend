package com.myappbbsbackend.api.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * viewartlikeinfo
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viewartlikeinfo implements Serializable {
    private Integer artid;

    private Integer likeuserid;

    private String username;

    private String userheadimg;

    private static final long serialVersionUID = 1L;
}