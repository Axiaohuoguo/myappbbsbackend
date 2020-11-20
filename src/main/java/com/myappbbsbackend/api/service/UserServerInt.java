package com.myappbbsbackend.api.service;

import com.myappbbsbackend.api.entity.CsUserinfo;
import org.springframework.stereotype.Service;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/11/19 12:55
 */
@Service
public interface UserServerInt {
    boolean userLogin(CsUserinfo userinfo);
    CsUserinfo getUserInfoByuserName(String username);

}
