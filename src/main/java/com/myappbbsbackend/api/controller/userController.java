package com.myappbbsbackend.api.controller;
import com.alibaba.fastjson.JSONObject;
import com.myappbbsbackend.api.entity.CsUserinfo;
import com.myappbbsbackend.api.service.TokenService;
import com.myappbbsbackend.api.service.UserServer;
import com.myappbbsbackend.planningcontrol.ApiResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/11/19 12:20
 */
@RestController
@RequestMapping(value={"/api/user"})
public class userController {

    @GetMapping("/hello")
    @ResponseBody
    public ApiResp apiHello()
    {
        ApiResp res = new ApiResp();
        res.setMsg("Hello");
        return res;
    }



    @Autowired(required = false)
    private  UserServer userServer;

    @Autowired(required = false)
    private TokenService tokenService;

    //    登录
    @PostMapping("/login")
    public ApiResp userLogin(@RequestBody JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request)
    {

        System.out.println(jsonObject.getString("username"));
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("userpassword");
        CsUserinfo csUserinfo = new CsUserinfo();
        csUserinfo.setUsername(username);
        csUserinfo.setUserpassword(password);
        if (userServer.userLogin(csUserinfo))
        {

            HttpSession httpSession =request.getSession();
            httpSession.setAttribute("user",username);

            csUserinfo = userServer.getUserInfoByuserName(username);

//            下发token
            Cookie cookie = new Cookie("token",
                    tokenService.getToken(csUserinfo,(boolean)jsonObject.get("isRemember")));
            Cookie cookie1 = new Cookie("signature",password );
            cookie.setPath("/");
            response.addCookie(cookie);
            response.addCookie(cookie1);
         return ApiResp.retOK(csUserinfo);
        }
        else {
            return ApiResp.retFail(505,"登录失败");
        }
    }
    //    注册

}
