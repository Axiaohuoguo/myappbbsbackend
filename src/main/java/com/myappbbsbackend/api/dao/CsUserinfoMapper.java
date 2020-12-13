package com.myappbbsbackend.api.dao;

import com.myappbbsbackend.api.entity.CsUserinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface CsUserinfoMapper {

    int userLogin(String username,String password);

    int selectUserByUserId(int id);

    CsUserinfo selectUserId(int  id);

    int deleteByPrimaryKey(Integer id);

    int userRegister(CsUserinfo record);

    int insertSelective(CsUserinfo record);

    CsUserinfo selectByUserName(String username);

    int updateByPrimaryKeySelective(CsUserinfo record);

    int updateByPrimaryKey(CsUserinfo record);

    int isRegisterName(CsUserinfo csUserinfo);

    int isRegisterEmail(CsUserinfo csUserinfo);

    int isRegisterPhone(CsUserinfo csUserinfo);

    int updateUserPasw(Map<String,Object> data);

    /**
     * 禁用
     * @param id
     * @return
     */
    int forbidden(int id);

    /**
     * 激活
     * @param id
     * @return
     */
    int nuforbidden(int id);

    /**
     * 成为超级管理
     * @param id
     * @return
     */
    int superUser(int id);

    /**
     * 成为普通管理
     * @param id
     * @return
     */
    int adminUser(int id);

}