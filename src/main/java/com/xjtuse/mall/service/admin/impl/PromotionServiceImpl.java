package com.xjtuse.mall.service.admin.impl;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.promotion.*;
import com.xjtuse.mall.customException.CustomException;
import com.xjtuse.mall.mapper.admin.PromotionMapper;
import com.xjtuse.mall.result.MapResultVo;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.admin.PromotionService;
import com.xjtuse.mall.utils.PageUtil;
import com.xjtuse.mall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    PromotionMapper mapper;

    @Override
    public MapResultVo queryAd(PageUtil pageUtil, Ad ad) {
        if (pageUtil.getLimit() != null) {
            pageUtil.initStart();
        }
        List<Ad> ads = mapper.queryAd(pageUtil, ad);
        int count = mapper.queryAdCount(pageUtil, ad);
        return ResultUtil.ok(ads, count);
    }

    @Override
    public TResultVo updateAd(Ad ad) {
        ad.setUpdateTime(new Date());
        mapper.updateAd(ad);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo deleteAD(Ad ad) {
        ad.setUpdateTime(new Date());
        ad.setDeleted(true);
        mapper.deleteAd(ad);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo createAd(Ad ad) {
        mapper.createAd(ad);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public MapResultVo queryCoupon(PageUtil pageUtil, Coupon coupon) {
        if (pageUtil.getLimit() != null) {
            pageUtil.initStart();
        }
        List<Coupon> coupons = mapper.queryCoupon(pageUtil, coupon);
        //检查优惠券过期情况
        int count = mapper.queryCouponCount(pageUtil, coupon);
        return ResultUtil.ok(coupons, count);
    }

    @Override
    public TResultVo queryCouponById(Coupon coupon) {
        Coupon couponById = mapper.queryCouponById(coupon);
        return ResultUtil.genSuccessResult(couponById);
    }

    @Override
    public TResultVo updateCoupon(Coupon coupon) {
        coupon.setUpdateTime(new Date());
        mapper.updateCoupon(coupon);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo deleteCoupon(Coupon coupon) {
        coupon.setUpdateTime(new Date());
        coupon.setDeleted(true);
        mapper.deleteCoupon(coupon);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo createCoupon(Coupon coupon) {
        coupon.setDeleted(false);
        Date date = new Date();
        coupon.setAddTime(date);
        coupon.setUpdateTime(date);
        mapper.createCoupon(coupon);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public MapResultVo queryCouponUser(PageUtil pageUtil, CouponAndUser couponAndUser) {
        if (pageUtil.getLimit() != null) {
            pageUtil.initStart();
        }
        List<CouponAndUser> couponAndUsers = mapper.queryCouponUser(pageUtil, couponAndUser);
        int count = mapper.queryCouponUserCount(pageUtil, couponAndUser);
        return ResultUtil.ok(couponAndUsers, count);
    }

    @Override
    public MapResultVo queryTopic(PageUtil pageUtil, Topic topic) {
        if (pageUtil.getLimit() != null) {
            pageUtil.initStart();
        }
        List<Topic> topics = mapper.queryTopic(pageUtil, topic);
        int count = mapper.queryTopicCount(pageUtil, topic);
        return ResultUtil.ok(topics, count);
    }

    @Override
    public TResultVo updateTopic(Topic topic) {
        topic.setUpdateTime(new Date());
        mapper.updateTopic(topic);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo deleteTopic(Topic topic) {
        topic.setUpdateTime(new Date());
        topic.setDeleted(true);
        mapper.deleteTopic(topic);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo createTopic(Topic topic) {
        Date date = new Date();
        topic.setAddTime(date);
        topic.setUpdateTime(date);
        topic.setDeleted(false);
        mapper.createTopic(topic);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public MapResultVo queryGroupon(PageUtil pageUtil, Groupon groupon, GrouponRules rules) {
        if (pageUtil.getLimit() != null) {
            pageUtil.initStart();
        }
        if(rules.getGoodsId() != null){
            //goodsId查找到rulesId
            Integer rulesId = mapper.queryRulesIdByGoodsId(rules);
            //利用rulesId进行groupon查找
            groupon.setRulesId(rulesId);
        }
        List<Object> result = new ArrayList<>();
        int count = mapper.queryGrouponCount(pageUtil, groupon);
        //查询所有grouponId
        List<Integer> ids = mapper.queryGrouponIdDistinct(pageUtil, groupon);
        for (Integer id :
                ids) {
            //将同一grouponId的条目组成subGroupons
            List<Groupon> groupons = mapper.queryGrouponByGid(id);
            //将条目中的第一个作为groupon
            Groupon firstGroupon = groupons.get(0);
            //查询对应rules添加作为rules
            GrouponRules queryRules = mapper.queryRelativeRules(firstGroupon.getRulesId());
            Map<String, Object> map = new HashMap<>();
            map.put("groupon", firstGroupon);
            map.put("subGroupons", groupons);
            map.put("rules", queryRules);
            result.add(map);
        }
        return ResultUtil.ok(result, count);
    }

    @Override
    public MapResultVo queryGrouponRules(PageUtil pageUtil, GrouponRules rules) {
        if (pageUtil.getLimit() != null) {
            pageUtil.initStart();
        }
        List<GrouponRules> grouponRules = mapper.queryGrouponRules(pageUtil, rules);
        int count = mapper.queryGrouponRulesCount(pageUtil, rules);
        return ResultUtil.ok(grouponRules, count);
    }

    @Override
    public TResultVo updateGrouponRules(GrouponRules rules) throws CustomException {
        Goods goods = mapper.queryGoodsById(rules);
        //商品可能不存在
        if(goods == null || goods.getName() == null){
            throw new CustomException("输入的商品ID不存在！");
        }
        rules.setGoodsName(goods.getName());
        rules.setPicUrl(goods.getPicUrl());
        rules.setUpdateTime(new Date());
        mapper.updateGrouponRules(rules);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo deleteGrouponRules(GrouponRules rules) {
        rules.setDeleted(true);
        rules.setUpdateTime(new Date());
        mapper.deleteGrouponRules(rules);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo createGrouponRules(GrouponRules rules) throws CustomException {
        Goods goods = mapper.queryGoodsById(rules);
        //商品可能不存在
        if(goods == null || goods.getName() == null){
            throw new CustomException("该商品不存在！");
        }
        Date date = new Date();
        rules.setAddTime(date);
        rules.setUpdateTime(date);
        rules.setDeleted(false);
        //设置商品名称,图片路径
        rules.setGoodsName(goods.getName());
        rules.setPicUrl(goods.getPicUrl());
        mapper.createGrouponRules(rules);
        return ResultUtil.genSuccessResult();
    }


}
