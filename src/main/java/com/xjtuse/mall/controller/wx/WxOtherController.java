package com.xjtuse.mall.controller.wx;


import com.xjtuse.mall.bean.promotion.Topic;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxOrderService;
import com.xjtuse.mall.service.wx.WxOtherService;
import com.xjtuse.mall.utils.PageUtil;
import com.xjtuse.mall.utils.ResultUtil;
import com.xjtuse.mall.utils.VoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wx")
public class WxOtherController {

    @Autowired
    WxOtherService otherService;

    @RequestMapping("/topic/list")
    public TResultVo topicList(PageUtil pageUtil){
        //查询topic数据
        List<Topic> topicList = otherService.queryTopicList(pageUtil, null);
        //查询全部topic数量
        Integer topicCount = otherService.queryTopicCount();
        //计算pages
        Integer pages = 1 + topicCount / pageUtil.getLimit();
        return ResultUtil.genSuccessResult(VoUtil.genListVo(
                topicCount,
                pages,
                pageUtil.getLimit(),
                pageUtil.getPage(),
                topicList
        ));
    }

    @RequestMapping("/topic/detail")
    public TResultVo topicDetail(Topic topic){
        //查询topic
        List<Topic> topicList = otherService.queryTopicList(null, topic);
        if(topicList.size()==1){
            Topic topic1 = topicList.get(0);
            String[] goods = topic1.getGoods();
            return ResultUtil.genSuccessResult(VoUtil.genTopicDetailVo(
                    topic1,
                    goods
            ));
        }
        return ResultUtil.genFailResult("没有相关专题!");
    }

    @RequestMapping("/topic/related")
    public TResultVo topicRelated(Topic topic){
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPage(1);
        pageUtil.setLimit(4);
        //查询topic
        List<Topic> topicList = otherService.queryTopicList(pageUtil, null);
        //查询topic 数量
        Integer count = otherService.queryTopicCount();
        //计算pages
        Integer pages = 1 + count / pageUtil.getLimit();

        return ResultUtil.genSuccessResult(VoUtil.genListVo(
                count,
                pages,
                pageUtil.getLimit(),
                pageUtil.getPage(),
                topicList
        ));
    }
}
