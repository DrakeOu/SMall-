package com.xjtuse.mall.service.wx.impl;

import com.xjtuse.mall.bean.mall.Issue;
import com.xjtuse.mall.bean.promotion.Ad;
import com.xjtuse.mall.bean.promotion.Topic;
import com.xjtuse.mall.mapper.wx.WxOtherMapper;
import com.xjtuse.mall.service.wx.WxOtherService;
import com.xjtuse.mall.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxOtherServiceImpl implements WxOtherService {

    @Autowired
    WxOtherMapper otherMapper;

    @Override
    public List<Ad> queryAllAd() {
        return otherMapper.queryAllAd();
    }

    @Override
    public List<Topic> queryCountTopic(int count) {
        return otherMapper.queryCountTopic(count);
    }

    @Override
    public List<Issue> queryAllIssue() {
        return otherMapper.queryAllIssue();
    }

    @Override
    public List<Topic> queryTopicList(PageUtil pageUtil, Topic topic) {
        if(pageUtil!=null && pageUtil.getLimit()!=null){
            pageUtil.initStart();
        }
        return otherMapper.queryTopicList(pageUtil, topic);
    }

    @Override
    public Integer queryTopicCount() {
        return otherMapper.queryTopicCount();
    }

    @Override
    public List<Issue> queryIssueList(String question, PageUtil pageUtil) {
        return otherMapper.queryIssueList(question, pageUtil);
    }
}
