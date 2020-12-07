package com.myappbbsbackend.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.myappbbsbackend.Interceptor.PassToken;
import com.myappbbsbackend.Interceptor.UserLoginToken;
import com.myappbbsbackend.api.entity.*;
import com.myappbbsbackend.api.service.ArticleServer;
import com.myappbbsbackend.planningcontrol.ApiResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/12/4 13:15
 */
@RestController
@RequestMapping(value={"/api/art"})
@CrossOrigin(
        origins = {"http://localhost:8100","http://*/*","http://localhost"},
        allowedHeaders = "*",
        allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST,
                RequestMethod.PUT, RequestMethod.DELETE,
                RequestMethod.OPTIONS, RequestMethod.HEAD}
)//允许跨域请求
public class ArtController {

    @Autowired(required = false)
    private ArticleServer articleServer;

    /**
     * 获得所有文章类型
     * @return
     */
    @PassToken
    @GetMapping("/gettype")
    public ApiResp getAllartType(){
        return ApiResp.retOK(articleServer.getArticleTypes());
    }

    /**
     * 图片，文件上传
     * @param file
     * @param request
     * @param response
     * @return
     */
    @UserLoginToken
    @PostMapping("/updateimg")
    public ApiResp uploadPicture(@RequestParam("file") MultipartFile file,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
    {
        String filename ;
        try {

            //上传目录地址
            String uploadDir = ResourceUtils.getURL("classpath:").getPath()+"resources/update/";
            System.out.println(uploadDir);
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if(!dir.exists())
            {
                dir.mkdir();
            }
            filename = executeUpload(uploadDir,file);

        }catch (Exception e){
            return ApiResp.retFail(505,"上传失败"+e.toString());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imgDir","/update/"+filename);

        return ApiResp.retOK(jsonObject);

    }

    /**
     * 提出上传的方法
     * @param uploadDir
     * @param file
     * @throws Exception
     */
    private String executeUpload(String uploadDir, MultipartFile file) throws Exception
    {
        String userId = "1";
        String artId = "2";
        //文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件名
        long strTime = new Date().getTime();
        String filename = userId +"_"+artId +"_" +strTime+suffix;
        //服务器端保存的文件对象
        File serverFile = new File(uploadDir + filename);
        //将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);
        return filename;
    }


    /**
     * 文章发布接口
     * @param jsonObject
     * @param request
     * @return
     */
    @UserLoginToken
    @PostMapping("/artpulish")
    public ApiResp artPulish(@RequestBody JSONObject jsonObject,HttpServletRequest request){
        if(jsonObject==null || jsonObject.toString()==""){
            return ApiResp.retFail(400,"内容为空");
        }
        String content = jsonObject.getString("editorData");
        String title = jsonObject.getString("artTitle");
        int userId = (int)jsonObject.get("authorId");
        int typeid = (int)jsonObject.get("typeId");
        CsArticleInfo csArticleInfo = new CsArticleInfo();
        csArticleInfo.setTypeid(typeid);
        Date date = new Date();
        csArticleInfo.setArteditime(date);
        csArticleInfo.setArttime(date);
        csArticleInfo.setArtcontent(content);
        csArticleInfo.setUserid(userId);
        csArticleInfo.setArttitle(title);
        if(articleServer.insertArticle(csArticleInfo)==1){

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("insertId",csArticleInfo.getId());
            return ApiResp.retOK(jsonObject1);
        }
        else {
            return ApiResp.retFail(505,"未知错误！");
        }

    }

    /**
     * 通过id查询文章信息
     * @param id
     * @ return
     */
    @PassToken
    @GetMapping("/getartinfobyid")
    public ApiResp getArtInfoById(@RequestParam("id") int id){

        Viewartinfo viewartinfo = articleServer.selectArticleById(id);
        if(viewartinfo==null ){
            return ApiResp.retFail(500,"没有相关数据");
        }
        return ApiResp.retOK(viewartinfo);
    }

    @GetMapping("/selectartlist")
    @PassToken
    public ApiResp selectArtListForPage(
            @RequestParam("page") int page
            ,@RequestParam("size") int size
            ,@RequestParam("schoolid") int schoolid){

        if(page==0 || size==0){
            return  ApiResp.retFail(400,"参数错误");
        }

        List<Viewartinfo> viewartinfoList =  articleServer.selectArticleByPage(page,size,schoolid);
        return ApiResp.retOK(viewartinfoList);
    }

    /**
     * 回复文章的接口
     * @param jsonObject
     * @return
     */
    @PostMapping("/replyart")
    @UserLoginToken
    public ApiResp replyArt(@RequestBody JSONObject jsonObject){
        CsReplyInfo csReplyInfo = new CsReplyInfo();
        csReplyInfo.setArtid((int)jsonObject.get("artId"));
        csReplyInfo.setReplycontent(jsonObject.getString("replyContent"));
        csReplyInfo.setReplyuserid((int)jsonObject.get("replyUserId"));
        csReplyInfo.setReplytime(new Date());
        if(articleServer.insertReply(csReplyInfo)==1){
            return ApiResp.retOK(csReplyInfo);
        }else {
         return ApiResp.retFail(505,"回复时发生错误");
        }
    }

    /**
     * 点赞或者取消点赞
     * @param artid
     * @param userid
     * @return
     */
    @GetMapping("/like")
    @UserLoginToken
    public ApiResp artLike(@RequestParam("artid") int artid,@RequestParam("userid") int userid){
        CsArtLikeInfo csArtLikeInfo = new CsArtLikeInfo();
        csArtLikeInfo.setArtid(artid);
        csArtLikeInfo.setLikeuserid(userid);
       String likestate = articleServer.artLike(csArtLikeInfo);
       JSONObject jsonObject = new JSONObject();
        switch(likestate){
            case "like" :
                jsonObject.put("msg","点赞成功");
                return ApiResp.retOK(jsonObject);
            case "likeF" :
                return ApiResp.retFail(505,"点赞失败未知错误");
            case "nulike" :
                jsonObject.put("msg","取消点赞成功");
                return ApiResp.retOK(jsonObject);
            case "nulikeF" :
                return ApiResp.retFail(505,"取消点赞失败未知错误");
            default : //可选
               return ApiResp.retFail(555,"不可能出现的错误！！！");
        }
    }

    /**
     * 获得文章回复的列表
     * @param artid
     * @return
     */
    @GetMapping("/getreplylist")
    @UserLoginToken
    public ApiResp getReplyList(@RequestParam("artid") int artid){
        List<Viewreplyinfo> viewreplyinfoList;
        viewreplyinfoList =  articleServer.getReplyInfolist(artid);
        return ApiResp.retOK(viewreplyinfoList);
    }

    /**
     * 判断用户是否对某文章点赞
     * @param artid
     * @param userid
     * @return
     */
    @GetMapping("/getislike")
    @UserLoginToken
    public ApiResp isLike(@RequestParam("artid") int artid, @RequestParam("userid") int userid){
        CsArtLikeInfo csArtLikeInfo = new CsArtLikeInfo();
        csArtLikeInfo.setLikeuserid(userid);
        csArtLikeInfo.setArtid(artid);
        JSONObject jsonObject = new JSONObject();
        if(articleServer.isLike(csArtLikeInfo)==1){
            jsonObject.put("islike",true);
            return ApiResp.retOK(jsonObject);
        }else {
            jsonObject.put("islike",false);
            return ApiResp.retOK(jsonObject);
        }

    }

    /**
     * 获得文章的点赞列表
     * @param artid
     * @return
     */
    @GetMapping("/getlikelist")
    @UserLoginToken
    public ApiResp getArtLikeList(@RequestParam("artid") int artid){
        List<Viewartlikeinfo> viewartlikeinfoList = articleServer.getArtLikeList(artid);
        return ApiResp.retOK(viewartlikeinfoList);
    }

}
