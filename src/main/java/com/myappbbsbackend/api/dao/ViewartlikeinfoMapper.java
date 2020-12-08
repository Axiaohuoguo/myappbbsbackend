package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.Viewartlikeinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewartlikeinfoMapper {
    int insert(Viewartlikeinfo record);

    int insertSelective(Viewartlikeinfo record);

    List<Viewartlikeinfo> selectArtLikeList(int artid);


}