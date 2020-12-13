package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.CsArticleInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CsArticleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsArticleInfo record);

    int insertSelective(CsArticleInfo record);

    CsArticleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsArticleInfo record);

    int updateByPrimaryKey(CsArticleInfo record);

    List<CsArticleInfo> selecArtListByUserid(Map<String,Object> data);

    int artCheck(int artid);

}