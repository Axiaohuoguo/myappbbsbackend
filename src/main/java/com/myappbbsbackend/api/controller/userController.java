package com.myappbbsbackend.api.controller;
import com.alibaba.fastjson.JSONObject;
import com.myappbbsbackend.Interceptor.AuthenticationInterceptor;
import com.myappbbsbackend.Interceptor.PassToken;
import com.myappbbsbackend.Interceptor.UserLoginToken;
import com.myappbbsbackend.api.entity.CsUserinfo;
import com.myappbbsbackend.api.entity.Viewuserinfo;
import com.myappbbsbackend.api.service.TokenService;
import com.myappbbsbackend.api.service.UserServer;
import com.myappbbsbackend.myutil.MyUtil;
import com.myappbbsbackend.planningcontrol.ApiResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/11/19 12:20
 */
@RestController
@RequestMapping(value={"/api/user"})
@CrossOrigin//允许跨域请求
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

    //    用户登录

    @PostMapping("/login")
    @PassToken
    public ApiResp userLogin(@RequestBody JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request){
        /**
         *  获取请求参数
         */
        System.out.println(jsonObject.getString("username"));
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        CsUserinfo csUserinfo = new CsUserinfo();
        csUserinfo.setUsername(username);
        csUserinfo.setUserpassword(new MyUtil().mD5Hash(password));
        HttpSession httpSession =request.getSession();
        /**
         * 判断是否能登陆
         */
        if (userServer.userLogin(csUserinfo))
        {


            httpSession.setAttribute("user",username);

            csUserinfo = userServer.getUserInfoByuserName(username);
            Viewuserinfo viewuserinfo = new Viewuserinfo();
            viewuserinfo = userServer.getUserInfoViewByUserName(username);
            //下发token、签名、
            Cookie cookie = new Cookie("token",
                    tokenService.getToken(csUserinfo,(boolean)jsonObject.get("isRemember")));
            Cookie cookie1 = new Cookie("signature",new MyUtil().mD5Hash(password) );
            cookie.setPath("/");
            response.addCookie(cookie);
            response.addCookie(cookie1);
         return ApiResp.retOK(viewuserinfo);
        }
        else {
            return ApiResp.retFail(505,"登录失败！账号或密码错误");
        }
    }

    /**
     * 退出登录
     * @ return
     */
    @GetMapping("/loginout")
    @ResponseBody
    public ApiResp loginOut()
    {
        return ApiResp.retOK();
    }

    /**
     * 免登陆验证
     * @param signature 签名
     * @param token token
     * @return
     */

    @GetMapping("/verification")
    @ResponseBody
    @UserLoginToken
    public ApiResp userVerification()
    {

//        @CookieValue("signature")  String signature ,
//        @CookieValue("token")String token
//        Cookie[] cookie = request.getCookies();
//        for (Cookie value : cookie) {
//            System.out.println(value.getName()+":"+value.getValue());
//
//
//        }

//        if(signature.equals("")|| token.equals(""))
//        {
//            return ApiResp.retFail(401,"身份验证失败");
//        }


        return ApiResp.retOK();

    }


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    @PassToken
    public ApiResp userRegister(@RequestBody JSONObject jsonObject)
    {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String schoolId = jsonObject.getString("schoolId");
        String phone = jsonObject.getString("phone");
//        String vcode = jsonObject.getString("vcode");
        String email = jsonObject.getString("email");
        CsUserinfo csUserinfo = new CsUserinfo();
        csUserinfo.setUsername(username);

        csUserinfo.setUserpassword(new MyUtil().mD5Hash(password));
        csUserinfo.setSchoolid(Integer.parseInt(schoolId));
        csUserinfo.setUserphone(phone);
        csUserinfo.setUseremail(email);
        String isregister =  userServer.isRegister(csUserinfo);
        if (isregister.equals("true"))
        {
            //执行注册
            if (userServer.userRegister(csUserinfo))
            {
                return ApiResp.retOK("注册成功");
            }
            return ApiResp.retFail(505,"未知错误请联系管理员");
        }
        else {
            return ApiResp.retFail(500,isregister+"已经存在");
        }
    }

}
