package com.myappbbsbackend.api.service;

import com.myappbbsbackend.api.dao.*;
import com.myappbbsbackend.api.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/12/4 13:29
 */

@Service
public class ArticleServer implements ArticleServerInt {

    @Autowired
    private CsArticleTypeMapper csArticleTypeMapper;

    @Autowired
    private CsArticleInfoMapper csArticleInfoMapper;

    @Autowired
    private ViewartinfoMapper viewartinfoMapper;

    @Autowired
    private CsReplyInfoMapper csReplyInfoMapper;

    @Autowired
    private CsArtLikeInfoMapper csArtLikeInfoMapper;

    @Autowired
    private ViewreplyinfoMapper viewreplyinfoMapper;

    @Autowired
    private ViewartlikeinfoMapper viewartlikeinfoMapper;

    @Override
    public List<CsArticleType> getArticleTypes() {
        return csArticleTypeMapper.selectAllType();
    }

    @Override
    public int insertArticle(CsArticleInfo csArticleInfo) {

        return csArticleInfoMapper.insert(csArticleInfo);
    }

    @Override
    public Viewartinfo selectArticleById(int id) {
        return viewartinfoMapper.selectArtInfoById(id);
    }

    /**
     * 分页查询数据
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Viewartinfo> selectArticleByPage(int page ,int size,int schoolid) {
        Map<String, Object> data = new HashMap();
        data.put("page",(page-1)*size);
        data.put("size",size);
        data.put("schoolid",schoolid);
        return viewartinfoMapper.selectArtInfolistByPage(data);
    }

    @Override
    public int insertReply(CsReplyInfo csReplyInfo) {
        return csReplyInfoMapper.insert(csReplyInfo) ;
    }

    /**
     * 点赞
     * @param csArtLikeInfo
     * @return
     */
    @Override
    public String artLike(CsArtLikeInfo csArtLikeInfo) {
        if(csArtLikeInfoMapper.isLike(csArtLikeInfo)==1){
            //已经点赞了取消点赞
            if(csArtLikeInfoMapper.deleteByArtAndUserid(csArtLikeInfo)==1){
                //取消点赞成功
                return "nulike";
            }
            return "nulikeF";
        }else {
            if(csArtLikeInfoMapper.insert(csArtLikeInfo) == 1){
                //点赞成功
            return "like";
            }
            //点赞失败
            return "likeF";
        }
    }

    /**
     * 获得回复列表
     * @param artid
     * @return
     */
    @Override
    public List<Viewreplyinfo> getReplyInfolist(int artid) {

        return viewreplyinfoMapper.getReplyinfolist(artid);
    }

    @Override
    public int isLike(CsArtLikeInfo csArtLikeInfo) {
        return csArtLikeInfoMapper.isLike(csArtLikeInfo);
    }

    @Override
    public List<Viewartlikeinfo> getArtLikeList(int artid) {

        return viewartlikeinfoMapper.selectArtLikeList(artid);
    }

    /***
     * 通过类型id查询文章列表
     * @param page
     * @param size
     * @param schoolid
     * @param typeid
     * @return
     */
    @Override
    public List<Viewartinfo> selectArticleByTypeId(int page, int size, int schoolid, int typeid) {
        Map<String, Object> data = new HashMap();
        data.put("page",(page-1)*size);
        data.put("size",size);
        data.put("schoolid",schoolid);
        data.put("typeid",typeid);
        return viewartinfoMapper.selectArtInfolistByTypeid(data);
    }

    @Override
    public List<CsArticleInfo> selecArtListByUserid(int userid) {
        return csArticleInfoMapper.selecArtListByUserid(userid);
    }


}
