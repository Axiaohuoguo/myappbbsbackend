package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.CsReReplyInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsReReplyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsReReplyInfo record);

    int insertSelective(CsReReplyInfo record);

    CsReReplyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsReReplyInfo record);

    int updateByPrimaryKey(CsReReplyInfo record);
}