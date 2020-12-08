package com.myappbbsbackend.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.myappbbsbackend.Interceptor.PassToken;
import com.myappbbsbackend.myutil.QiniuCloudUtil;
import com.myappbbsbackend.planningcontrol.ApiResp;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/12/8 20:42
 */
@RestController
@RequestMapping(value={"/api/upload"})
@CrossOrigin(
        origins = {"http://localhost:8100","http://*/*","http://localhost"},
        allowedHeaders = "*",
        allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST,
                RequestMethod.PUT, RequestMethod.DELETE,
                RequestMethod.OPTIONS, RequestMethod.HEAD}
)//允许跨域请求
public class QiniuController {
    @ResponseBody
    @PassToken
    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
    public ApiResp uploadImg(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ApiResp.retFail(400,"不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        try {
            byte[] bytes = file.getBytes();
            String imageName = UUID.randomUUID()+ "_myapp_"+ new Date().getTime();
            try {
                //使用base64方式上传到七牛云
                System.out.println(imageName);

                String url = QiniuCloudUtil.put64image(bytes, imageName);
                System.out.println("http://"+url);

                jsonObject.put("imgDir",imageName);
            } catch (Exception e) {
                e.printStackTrace();
                return ApiResp.retFail(502,e.toString());
            }
        } catch (IOException e) {
            return ApiResp.retFail(400,"上传图片异常");
        }
        return ApiResp.retOK(jsonObject);
    }
}
