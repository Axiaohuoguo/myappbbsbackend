package com.myappbbsbackend.api.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cs_shool
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsShool implements Serializable {
    /**
     * 学校id
     */
    private Integer schoolid;

    /**
     * 学校名称
     */
    private String shoolname;

    /**
     * 简称
     */
    private String abbreviation;

    private static final long serialVersionUID = 1L;
}