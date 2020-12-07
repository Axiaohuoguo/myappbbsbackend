package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.CsReplyInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsReplyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsReplyInfo record);

    int insertSelective(CsReplyInfo record);

    CsReplyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsReplyInfo record);

    int updateByPrimaryKey(CsReplyInfo record);
}