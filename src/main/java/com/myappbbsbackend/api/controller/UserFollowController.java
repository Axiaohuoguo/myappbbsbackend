package com.myappbbsbackend.api.controller;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.myappbbsbackend.Interceptor.UserLoginToken;
import com.myappbbsbackend.api.entity.CsFollowInfo;
import com.myappbbsbackend.api.entity.Viewfserfollowinfo;
import com.myappbbsbackend.api.service.UserFollowServer;
import com.myappbbsbackend.planningcontrol.ApiResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Description: 用户关注控制器
 * @ Author: 小火锅
 * @ Date: 2020/12/11 18:20
 */
@RestController
@RequestMapping(value={"/api/user"})
@CrossOrigin(
        origins = {"http://localhost:8100","http://*/*","http://localhost"},
        allowedHeaders = "*",
        allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST,
                RequestMethod.PUT, RequestMethod.DELETE,
                RequestMethod.OPTIONS, RequestMethod.HEAD}
)//允许跨域请求
public class UserFollowController {
    @Autowired
    private UserFollowServer userFollowServer;

    @GetMapping("/isfollow")
    @UserLoginToken
    public ApiResp isFollow(
            @RequestParam("myid") int myuserid,
            @RequestParam("taid") int tauserid){
        JSONObject jsonObject = new JSONObject();
        if(userFollowServer.isFollow(myuserid,tauserid)==1){
            jsonObject.put("state",true);
            return ApiResp.retOK(jsonObject);
        }
        jsonObject.put("state",false);
        return ApiResp.retOK(jsonObject);
    }

    @GetMapping("/follow")
    @UserLoginToken
    public ApiResp follow(@RequestParam("myid") int myuserid,
                          @RequestParam("taid") int tauserid){
        // 我要关注你就是我是粉丝
        CsFollowInfo csFollowInfo = new CsFollowInfo();
        csFollowInfo.setMyuserid(tauserid);
        csFollowInfo.setFansid(myuserid);
        if(userFollowServer.isFollow(myuserid,tauserid)==1){
            return ApiResp.retFail(400,"关注失败！你已经关注TA了");
        }

        if(userFollowServer.follow(csFollowInfo)==1){
            return ApiResp.retOK();
        }
        else return ApiResp.retFail(400,"关注失败!未知错误");

    }

    //取消关注
    @GetMapping("/unfollow")
    @UserLoginToken
    public ApiResp unfollow(@RequestParam("myid") int myuserid,
                            @RequestParam("taid") int tauserid){

        if(userFollowServer.unfollow(myuserid,tauserid)==1){
            return ApiResp.retOK();
        }
        else return ApiResp.retFail(400,"取关失败");

    }

    @GetMapping("/getfollowlist")
    @UserLoginToken
    public ApiResp getfollowlist(@RequestParam("myid") int myuserid){
        List<Viewfserfollowinfo> viewfserfollowinfoList ;
        viewfserfollowinfoList = userFollowServer.getfollowidlist(myuserid);
        return ApiResp.retOK(viewfserfollowinfoList);
    }

    @GetMapping("/getfanslist")
    @UserLoginToken
    public ApiResp getfanslist(@RequestParam("myid") int myuserid){
        List<Viewfserfollowinfo> viewfserfollowinfoList ;
        viewfserfollowinfoList = userFollowServer.getfansidlist(myuserid);
        return ApiResp.retOK(viewfserfollowinfoList);
    }

    @GetMapping("/getfollownum")
    @UserLoginToken
    public ApiResp getfollownum(@RequestParam("myid") int myuserid){

        return ApiResp.retOK(userFollowServer.getfollownum(myuserid));
    }

    @GetMapping("/getfansnum")
    @UserLoginToken
    public ApiResp getfansnum(@RequestParam("myid") int myuserid){
        return ApiResp.retOK(userFollowServer.getfansnum(myuserid));
    }

}
