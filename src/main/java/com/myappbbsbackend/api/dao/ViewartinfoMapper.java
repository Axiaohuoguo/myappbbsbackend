package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.Viewartinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ViewartinfoMapper {
    int insert(Viewartinfo record);

    int insertSelective(Viewartinfo record);

    Viewartinfo selectArtInfoById(int id);

    List<Viewartinfo> selectArtInfolistByPage(Map<String,Object> data);
}