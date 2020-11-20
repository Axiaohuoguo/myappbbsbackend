package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.CsUserinfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsUserinfoMapper {

    int userLogin(String username,String password);

    int deleteByPrimaryKey(Integer id);

    int insert(CsUserinfo record);

    int insertSelective(CsUserinfo record);

    CsUserinfo selectByUserName(String username);

    int updateByPrimaryKeySelective(CsUserinfo record);

    int updateByPrimaryKey(CsUserinfo record);
}