package com.myappbbsbackend.api.service;

import com.myappbbsbackend.api.entity.CsUserinfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/11/19 15:04
 */
@Service
public class TokenService implements TokenServiceInt{
    /**
     *  token 下发
     * @param csUserinfo
     * @param isRemember
     * @return
     */
    @Override
    public String getToken(CsUserinfo csUserinfo,boolean isRemember) {

        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        long scurrentTime = System.currentTimeMillis() + 60* 60 * 1000*24*7;//三天有效时间
        Date end ;
        if (! isRemember)
        {
            end = new Date(currentTime);
        }
        else {
            end = new Date(scurrentTime);
        }

        JwtBuilder jwtBuilder =  Jwts.builder()
                .setId(csUserinfo.getId().toString())
                .setSubject(csUserinfo.getUsername())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,csUserinfo.getUserpassword())
                .setExpiration(end);
        System.out.println(jwtBuilder.compact());
        return jwtBuilder.compact();
    }

//    @Override
//    public JwtBuilder decodeTokenJ(String tokens) {
////        Claims claims = Jwts.parser();
//        return null;
//    }

}
