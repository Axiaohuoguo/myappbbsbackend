package com.myappbbsbackend.api.service;

import com.myappbbsbackend.api.entity.CsUserinfo;
import io.jsonwebtoken.JwtBuilder;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/11/19 15:04
 */
public interface TokenServiceInt {
    String getToken (CsUserinfo csUserinfo,boolean isRemember);

//    JwtBuilder decodeTokenJ (String tokens);


}
