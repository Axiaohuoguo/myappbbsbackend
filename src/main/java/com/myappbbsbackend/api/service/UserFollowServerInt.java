package com.myappbbsbackend.api.service;

import com.myappbbsbackend.api.entity.CsFollowInfo;
import com.myappbbsbackend.api.entity.Viewfserfollowinfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/12/11 20:18
 */
@Service
public interface UserFollowServerInt {
    int follow(CsFollowInfo csFollowInfo);

    int unfollow(int myuserid,int tauserid);

    int isFollow(int myid, int taid);

    List<Viewfserfollowinfo> getfollowidlist(int userid);

    List<Viewfserfollowinfo> getfansidlist(int userid);

    int getfollownum(int userid);

    int getfansnum(int userid);

}
