package com.myappbbsbackend.api.service;

import com.myappbbsbackend.api.entity.CsUserinfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;

import java.io.UnsupportedEncodingException;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/11/19 15:04
 */
public interface TokenServiceInt {
    String getToken (CsUserinfo csUserinfo,boolean isRemember) throws UnsupportedEncodingException;

    Claims decodeTokenJ (String tokens, String secretKey) throws UnsupportedEncodingException, Exception;


}
