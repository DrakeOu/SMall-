package com.xjtuse.mall.mapper.wx;

import com.xjtuse.mall.bean.mall.Issue;
import com.xjtuse.mall.bean.promotion.Ad;
import com.xjtuse.mall.bean.promotion.Topic;
import com.xjtuse.mall.utils.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxOtherMapper {
    List<Ad> queryAllAd();

    List<Topic> queryCountTopic(int count);

    List<Issue> queryAllIssue();

    List<Topic> queryTopicList(@Param("pageUtil") PageUtil pageUtil, @Param("topic") Topic topic);

    Integer queryTopicCount();

    List<Issue> queryIssueList(String question, PageUtil pageUtil);

    /**
     * 此mapper及对应service控制 如下表数据
     * ad
     * topic
     * keyword
     * storage
     * issue
     * system
     * region
     */

}
