package com.myappbbsbackend.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.myappbbsbackend.api.service.TokenService;
import com.myappbbsbackend.api.service.UserServer;
import com.myappbbsbackend.planningcontrol.ApiResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;


/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/11/22 19:39
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserServer userServer;

    @Autowired
    TokenService tokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        // 检查方法是否有 PassToken 注释 ，有则直接跳过验证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 解析cookie
        JSONObject jsonObject = new JSONObject();
        Cookie[] cookies = request.getCookies();
        if (cookies==null){
            //拦截后返回错误信息
            returnJson(response);
            return false;
        }
        else {
            for (Cookie value : cookies) {
                jsonObject.put(value.getName(),value.getValue());
            }
        }
        System.out.println(jsonObject);
        String token = jsonObject.getString("token");
        String signature = jsonObject.getString("signature");

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()){
                //执行认证
                if(token == null || token.equals("") || signature ==null || signature.equals("")){
                    returnJson(response);
                    return false;
                }

                // 获取 token 中的 user id
                String userId =  tokenService.decodeTokenJ(token,signature).getId();
                if (userId == null || userId.equals("")){
                    returnJson(response);
                    return false;
                }else {
                    //验证token
                    int isHave = userServer.selectUserByUserId(Integer.parseInt(userId));
                    if(isHave == 1 ){
                        return true;
                    }
                    returnJson(response);
                    return false;
                }
            }
            returnJson(response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void returnJson(HttpServletResponse response){
        response.setStatus(401);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",401);
            jsonObject.put("msg","身份验证失败！");
            writer.print(jsonObject);
        } catch (IOException e){

        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}
