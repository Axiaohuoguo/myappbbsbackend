package com.myappbbsbackend.api.service;

import com.myappbbsbackend.api.dao.CsUserinfoMapper;
import com.myappbbsbackend.api.entity.CsUserinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/11/19 12:55
 */
@Service
public class UserServer implements UserServerInt {

    @Autowired
    private CsUserinfoMapper csUserinfoMapper;
    @Override
    public boolean userLogin(CsUserinfo userinfo) {
        if (userinfo.getUsername().equals("") || userinfo.getUserpassword().equals("")|| userinfo.getUserpassword()==null || userinfo.getUsername()==null )
        {
            return false;
        }
        else {
            return (csUserinfoMapper.userLogin(userinfo.getUsername(),userinfo.getUserpassword())==1);
        }
    }

    @Override
    public CsUserinfo getUserInfoByuserName(String username) {
        return csUserinfoMapper.selectByUserName(username);
    }

}
