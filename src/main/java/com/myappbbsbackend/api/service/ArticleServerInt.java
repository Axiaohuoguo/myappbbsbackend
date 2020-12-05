package com.myappbbsbackend.api.service;

import com.myappbbsbackend.api.entity.CsArticleInfo;
import com.myappbbsbackend.api.entity.CsArticleType;
import com.myappbbsbackend.api.entity.Viewartinfo;

import java.util.List;
import java.util.Map;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/12/4 13:30
 */
public interface ArticleServerInt {
    List<CsArticleType> getArticleTypes();

    int insertArticle(CsArticleInfo csArticleInfo);

    Viewartinfo selectArticleById(int id);

    List<Viewartinfo> selectArticleByPage(int page,int size,int schoolid);
}
