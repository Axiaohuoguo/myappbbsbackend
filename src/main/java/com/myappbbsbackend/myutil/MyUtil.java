package com.myappbbsbackend.myutil;

import org.springframework.util.DigestUtils;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/11/20 15:59
 */
public class MyUtil {

    public String mD5Hash(String str)
    {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
