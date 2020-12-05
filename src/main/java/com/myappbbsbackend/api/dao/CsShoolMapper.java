package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.CsShool;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsShoolMapper {

    int deleteByPrimaryKey(Integer schoolid);

    int insert(CsShool record);

    int insertSelective(CsShool record);

    CsShool selectByPrimaryKey(Integer schoolid);

    List<CsShool> selectAllSchool();

    int updateByPrimaryKeySelective(CsShool record);

    int updateByPrimaryKey(CsShool record);
}