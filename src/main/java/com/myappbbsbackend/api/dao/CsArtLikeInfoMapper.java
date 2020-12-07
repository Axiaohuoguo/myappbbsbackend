package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.CsArtLikeInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsArtLikeInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByArtAndUserid(CsArtLikeInfo record);

    int insert(CsArtLikeInfo record);

    int insertSelective(CsArtLikeInfo record);

    CsArtLikeInfo selectByPrimaryKey(Integer id);

    int isLike (CsArtLikeInfo record);

    int updateByPrimaryKeySelective(CsArtLikeInfo record);

    int updateByPrimaryKey(CsArtLikeInfo record);
}