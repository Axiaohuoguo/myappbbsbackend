package com.myappbbsbackend.api.service;

import com.myappbbsbackend.api.dao.CsShoolMapper;
import com.myappbbsbackend.api.entity.CsShool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/11/26 15:33
 */
@Service
public class ShoolServer implements  ShoolServerInt{

    @Autowired
    private CsShoolMapper csShoolMapper;

    @Override
    public List<CsShool> getShool() {

        return csShoolMapper.selectAllSchool();
    }
}
