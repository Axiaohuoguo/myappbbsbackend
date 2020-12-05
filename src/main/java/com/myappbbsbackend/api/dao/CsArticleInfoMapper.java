package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.CsArticleInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsArticleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsArticleInfo record);

    int insertSelective(CsArticleInfo record);

    CsArticleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsArticleInfo record);

    int updateByPrimaryKey(CsArticleInfo record);
}