package com.myappbbsbackend.api.service;

import com.myappbbsbackend.api.dao.CsArticleInfoMapper;
import com.myappbbsbackend.api.dao.CsArticleTypeMapper;
import com.myappbbsbackend.api.dao.ViewartinfoMapper;
import com.myappbbsbackend.api.entity.CsArticleInfo;
import com.myappbbsbackend.api.entity.CsArticleType;
import com.myappbbsbackend.api.entity.Viewartinfo;
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


}
