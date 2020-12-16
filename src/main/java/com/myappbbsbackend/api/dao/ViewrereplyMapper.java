package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.Viewrereply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewrereplyMapper {
    int insert(Viewrereply record);

    int insertSelective(Viewrereply record);

    List<Viewrereply> getReReplylist (int replyid);
}