package com.xjtuse.mall.VO;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.promotion.Topic;

import java.util.List;

public class TopicDetailVo {

    private Topic topic;
    private String[] goods;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String[] getGoods() {
        return goods;
    }

    public void setGoods(String[] goods) {
        this.goods = goods;
    }
}
