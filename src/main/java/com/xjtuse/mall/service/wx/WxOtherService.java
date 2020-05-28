package com.xjtuse.mall.service.wx;

import com.xjtuse.mall.bean.mall.Issue;
import com.xjtuse.mall.bean.promotion.Ad;
import com.xjtuse.mall.bean.promotion.Topic;
import com.xjtuse.mall.utils.PageUtil;

import java.util.List;

public interface WxOtherService {
    List<Ad> queryAllAd();

    List<Topic> queryCountTopic(int count);

    List<Issue> queryAllIssue();

    List<Topic> queryTopicList(PageUtil pageUtil, Topic topic);

    Integer queryTopicCount();

}
