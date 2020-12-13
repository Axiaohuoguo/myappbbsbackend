package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.CsGlsqb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsGlsqbMapper {
    /**
     * 驳回申请
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 提交申请
     */
    int insert(CsGlsqb record);

    int insertSelective(CsGlsqb record);

    CsGlsqb selectByPrimaryKey(Integer id);

    int selectIsSubmit(int userid);

    int updateByPrimaryKeySelective(CsGlsqb record);

    int updateByPrimaryKey(CsGlsqb record);

    /**
     * 获得申请列表
     * @return
     */
    List<CsGlsqb> getAlllist();

    /**
     * 通过审核
     * @param userid
     * @return
     */
    int appTaAdmin(int userid);



}