package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.CsFollowInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface CsFollowInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsFollowInfo record);

    //     // 我是15 他是 22 要取消关注
    //    DELETE TABLE cs_follow_info
    //    WHERE userId = 22 and fansid = 15
    //int myid,int taid
    int unfollow(Map<String, Object> data);

    int insertSelective(CsFollowInfo record);

    CsFollowInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsFollowInfo record);

    int updateByPrimaryKey(CsFollowInfo record);

    int isFollower(Map<String, Object> data);

    int fansnumber(int userid);

    int follownumber(int userid);
}