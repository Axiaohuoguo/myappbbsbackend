package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.Viewfserfollowinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewfserfollowinfoMapper {
    int insert(Viewfserfollowinfo record);

    int insertSelective(Viewfserfollowinfo record);

    List<Viewfserfollowinfo> selectMyFollowlist(int userId);

    List<Viewfserfollowinfo> selectMyFanslist(int userId);

}