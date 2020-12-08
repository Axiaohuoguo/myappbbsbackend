package com.myappbbsbackend.api.service;

import com.myappbbsbackend.api.entity.*;

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

    int insertReply(CsReplyInfo csReplyInfo);

    String artLike(CsArtLikeInfo csArtLikeInfo);

    List<Viewreplyinfo> getReplyInfolist(int artid);

    int isLike(CsArtLikeInfo csArtLikeInfo);

    List<Viewartlikeinfo> getArtLikeList(int artid);

    List<Viewartinfo> selectArticleByTypeId(int page, int size, int schoolid, int typeid);

    List<CsArticleInfo> selecArtListByUserid(int userid);

}
