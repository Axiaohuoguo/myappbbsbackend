package com.myappbbsbackend.api.service;

import com.myappbbsbackend.api.entity.CsGlsqb;
import com.myappbbsbackend.api.entity.CsUserinfo;
import com.myappbbsbackend.api.entity.Viewuserinfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/11/19 12:55
 */
@Service
public interface UserServerInt {
    boolean userLogin(CsUserinfo userinfo);
    CsUserinfo getUserInfoByuserName(String username);
    Viewuserinfo getUserInfoViewByUserName(String username);

    String isRegister(CsUserinfo userinfo);
    boolean userRegister(CsUserinfo userinfo);

    int selectUserByUserId(int id);

    CsUserinfo getUserInfoByuserid(int id);

    int updateUserInfo(CsUserinfo csUserinfo);

    int updateUserPaw(String password ,int userid);

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

    /**
     * 通过管理审核
     * @param userid
     * @return
     */
    int appTaAdmin(int userid);

    /**
     * 申请管理列表
     * @return
     */
    List<CsGlsqb> getAlllist();

    /**
     * 提交申请
     * @param csGlsqb
     * @return
     */
    int submitAdmin(CsGlsqb csGlsqb);

    /**
     * 驳回申请
     * @param id
     * @return
     */
    int rejectAdmin(int id);

    int selectIsSubmit(int userid);





}
