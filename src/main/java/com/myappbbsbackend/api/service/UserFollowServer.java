package com.myappbbsbackend.api.service;

import com.myappbbsbackend.api.dao.CsFollowInfoMapper;
import com.myappbbsbackend.api.dao.ViewfserfollowinfoMapper;
import com.myappbbsbackend.api.entity.CsFollowInfo;
import com.myappbbsbackend.api.entity.Viewfserfollowinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Description: 关注服务
 * @ Author: 小火锅
 * @ Date: 2020/12/11 20:19
 */
@Service
public class UserFollowServer implements UserFollowServerInt{

    @Autowired
    private CsFollowInfoMapper csFollowInfoMapper;

    @Autowired
    private ViewfserfollowinfoMapper viewfserfollowinfoMapper;

    @Override
    public int follow(CsFollowInfo csFollowInfo) {
        return csFollowInfoMapper.insert(csFollowInfo);
    }

    @Override
    public int unfollow(int myid,int taid) {
        Map<String, Object> data = new HashMap<>();
        data.put("myid",myid);
        data.put("taid",taid);
        return csFollowInfoMapper.unfollow(data);
    }

    @Override
    public int isFollow(int myid, int taid) {
        Map<String, Object> data = new HashMap<>();
        data.put("myid",myid);
        data.put("taid",taid);
        return csFollowInfoMapper.isFollower(data);
    }

    @Override
    public List<Viewfserfollowinfo> getfollowidlist(int userid) {
        return viewfserfollowinfoMapper.selectMyFollowlist(userid);
    }

    @Override
    public List<Viewfserfollowinfo> getfansidlist(int userid) {
        return viewfserfollowinfoMapper.selectMyFanslist(userid);
    }

    @Override
    public int getfollownum(int userid) {
        return csFollowInfoMapper.follownumber(userid);
    }

    @Override
    public int getfansnum(int userid) {
        return csFollowInfoMapper.fansnumber(userid);
    }
}
