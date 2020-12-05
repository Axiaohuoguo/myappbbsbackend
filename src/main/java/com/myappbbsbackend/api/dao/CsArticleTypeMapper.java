package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.CsArticleType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CsArticleTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsArticleType record);

    int insertSelective(CsArticleType record);

    CsArticleType selectByPrimaryKey(Integer id);

    List<CsArticleType> selectAllType();

    int updateByPrimaryKeySelective(CsArticleType record);

    int updateByPrimaryKey(CsArticleType record);
}