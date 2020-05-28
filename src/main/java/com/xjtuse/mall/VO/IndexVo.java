package com.xjtuse.mall.VO;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.mall.Brand;
import com.xjtuse.mall.bean.mall.Category;
import com.xjtuse.mall.bean.promotion.Ad;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.promotion.Groupon;
import com.xjtuse.mall.bean.promotion.Topic;

import java.util.List;

public class IndexVo {

    private List<Goods> newGoodsList;

    private List<Coupon> couponList;

    private List<Category> channel;

    private List<Groupon> grouponList;

    private List<Ad> banner;

    private List<Brand> brandList;

    private List<Goods> hotGoodsList;

    private List<Topic> topicList;

    public List<Goods> getNewGoodsList() {
        return newGoodsList;
    }

    public void setNewGoodsList(List<Goods> newGoodsList) {
        this.newGoodsList = newGoodsList;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public List<Category> getChannel() {
        return channel;
    }

    public void setChannel(List<Category> channel) {
        this.channel = channel;
    }

    public List<Groupon> getGrouponList() {
        return grouponList;
    }

    public void setGrouponList(List<Groupon> grouponList) {
        this.grouponList = grouponList;
    }

    public List<Ad> getBanner() {
        return banner;
    }

    public void setBanner(List<Ad> banner) {
        this.banner = banner;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public List<Goods> getHotGoodsList() {
        return hotGoodsList;
    }

    public void setHotGoodsList(List<Goods> hotGoodsList) {
        this.hotGoodsList = hotGoodsList;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }
}
