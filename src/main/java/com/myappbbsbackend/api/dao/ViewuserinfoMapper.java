package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.Viewuserinfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ViewuserinfoMapper {
    int insert(Viewuserinfo record);

    int insertSelective(Viewuserinfo record);

    Viewuserinfo selectUserInfoByUserName(String username);
}