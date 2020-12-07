package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.Viewreplyinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewreplyinfoMapper {
    int insert(Viewreplyinfo record);

    int insertSelective(Viewreplyinfo record);

    List<Viewreplyinfo> getReplyinfolist(int artid);
}