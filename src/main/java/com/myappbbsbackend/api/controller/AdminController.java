package com.myappbbsbackend.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.myappbbsbackend.Interceptor.UserLoginToken;
import com.myappbbsbackend.api.entity.CsGlsqb;
import com.myappbbsbackend.api.service.ArticleServer;
import com.myappbbsbackend.api.service.UserServer;
import com.myappbbsbackend.planningcontrol.ApiResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @ Description: 管理控制
 * @ Author: 小火锅
 * @ Date: 2020/12/12 17:03
 */
@RestController
@RequestMapping(value={"/api/admin"})
@CrossOrigin(
        origins = {"http://localhost:8100","http://*/*","http://localhost"},
        allowedHeaders = "*",
        allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST,
                RequestMethod.PUT, RequestMethod.DELETE,
                RequestMethod.OPTIONS, RequestMethod.HEAD}
)//允许跨域请求
public class AdminController {
    @Autowired
    private UserServer userServer;

    @Autowired
    private ArticleServer articleServer;

    /**
     *  帖子状态 -   1==正常  2==待审核 3==审核未通过
     *  用户类型 1-普通用户 ，2-万能墙管理员， 999 - 系统管理员，‘-1’-禁用
     */


    /**
     * 警用/或者激活某人的账号
     * @param myuserid
     * @param tauserid
     * @return
     */
    @UserLoginToken
    @GetMapping("/forbidden")
    public ApiResp forbidden(@RequestParam("myid") int myuserid,
                             @RequestParam("taid") int tauserid){
        JSONObject jsonObject = new JSONObject();
        if(!userServer.getUserInfoByuserid(myuserid).getUsertype().equals("999")){
            return ApiResp.retFail(400,"你不是管理员");
        }
        //看看是否封号-激活 或者封号
        if(userServer.getUserInfoByuserid(tauserid).getUsertype().equals("-1")||userServer.getUserInfoByuserid(tauserid).getUsertype().equals("2")){
            if(userServer.nuforbidden(tauserid)==1){
                jsonObject.put("msg","激活成功");
                return ApiResp.retOK(jsonObject);
            }else { return ApiResp.retFail(400,"激活失败"); }

        }if(userServer.getUserInfoByuserid(tauserid).getUsertype().equals("999")){
            jsonObject.put("msg","他是超级管理你不能对她操作");
            return ApiResp.retOK(jsonObject);
        }if(userServer.getUserInfoByuserid(tauserid).getUsertype().equals("1")||userServer.getUserInfoByuserid(tauserid).getUsertype().equals("2")){
            if(userServer.forbidden(tauserid)==1){
                jsonObject.put("msg","禁用成功");
                return ApiResp.retOK(jsonObject);
            }else { return ApiResp.retFail(400,"禁用失败"); }

        }
        return ApiResp.retFail(500,"未知错误");
    }

    /**
     * 文章审核
     * @param myuserid
     * @param artid
     * @return
     */
    @UserLoginToken
    @GetMapping("/artcheck")
    public ApiResp artCheck(@RequestParam("myid") int myuserid,
                            @RequestParam("artid") int artid)
    {
        String aa= userServer.getUserInfoByuserid(myuserid).getUsertype();
        if( !userServer.getUserInfoByuserid(myuserid).getUsertype().equals("999") && !userServer.getUserInfoByuserid(myuserid).getUsertype().equals("2")){
            return ApiResp.retFail(400,"你不是管理哦");
        }
        if(articleServer.artCheck(artid)==1){
            return ApiResp.retOK("操作成功");
        }

        return ApiResp.retOK("操作失败");
    }

    /**
     * 获得所有待审核文章
     * @return
     */
    @UserLoginToken
    @GetMapping("/getallcheck")
    public ApiResp  getAllCheckList()
    {
        return ApiResp.retOK(articleServer.getAllCheckList());
    }

    /**
     * 获得所有待审核文章 - 通过学校id
     * @return
     */
    @UserLoginToken
    @GetMapping("/getshoolcheck")
    public ApiResp  getschoolCheckList(@RequestParam("shoolid") int shoolid)
    {
        return ApiResp.retOK(articleServer.getAllCheckListByschool(shoolid));
    }

    /**
     * 申请成为管理员
     * @param jsonObject
     * @return
     */
    @UserLoginToken
    @PostMapping("/applyadmin")
    public ApiResp applyAdmin(@RequestBody JSONObject jsonObject){

        if(userServer.selectIsSubmit((int)jsonObject.get("userid"))==1){
            return ApiResp.retFail(400,"请勿重复提交");
        }
        CsGlsqb csGlsqb = new CsGlsqb();
        csGlsqb.setUserid((int)jsonObject.get("userid"));
        csGlsqb.setContent(jsonObject.getString("content"));
        csGlsqb.setGlstate(1);
        if(userServer.submitAdmin(csGlsqb)==1){
            return ApiResp.retOK();
        }
        return ApiResp.retFail(400,"提交失败");

    }

    /**
     * 获得申请管理的列表
     * @return
     */
    @UserLoginToken
    @GetMapping("/getapplyadminlist")
    public ApiResp getapplyAdminList(){
        return ApiResp.retOK(userServer.getAlllist());
    }

    /**
     * 通过审核并 使ta成为管理员
     * @param myuserid
     * @param taid
     * @return
     */
    @UserLoginToken
    @GetMapping("/apply")
    public ApiResp apply(@RequestParam("myid") int myuserid,
                         @RequestParam("taid") int taid){
        if( !userServer.getUserInfoByuserid(myuserid).getUsertype().equals("999")){
            return ApiResp.retFail(400,"你不是管理哦");
        }
        else {
            userServer.appTaAdmin(taid);
            if(userServer.adminUser(taid)==1){
                return ApiResp.retOK();
            }

        }
        return ApiResp.retFail(400,"操作失败");

    }




}
