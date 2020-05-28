package com.xjtuse.mall.controller.admin;

import com.xjtuse.mall.bean.promotion.*;
import com.xjtuse.mall.customException.CustomException;
import com.xjtuse.mall.result.MapResultVo;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.admin.PromotionService;
import com.xjtuse.mall.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class PromotionController {

    @Autowired
    PromotionService service;

    //查询广告
    @RequestMapping("/ad/list")
    public MapResultVo queryAd(PageUtil pageUtil, Ad ad){
        return service.queryAd(pageUtil, ad);
    }

    //更新广告
    @RequestMapping("/ad/update")
    public TResultVo updateAd(@RequestBody Ad ad){
        return service.updateAd(ad);
    }

    //删除广告
    @RequestMapping("/ad/delete")
    public TResultVo deleteAd(@RequestBody Ad ad){
        return service.deleteAD(ad);
    }

    //创建广告
    @RequestMapping("/ad/create")
    public TResultVo createAd(@RequestBody Ad ad){
        return service.createAd(ad);
    }

    //查询优惠券
    @RequestMapping("/coupon/list")
    public MapResultVo queryCoupon(PageUtil pageUtil, Coupon coupon){
        return service.queryCoupon(pageUtil, coupon);
    }

    //更新优惠券
    @RequestMapping("/coupon/update")
    public TResultVo updateCoupon(@RequestBody Coupon coupon){
        return service.updateCoupon(coupon);
    }

    //更新优惠券
    @RequestMapping("/coupon/delete")
    public TResultVo deleteCoupon(@RequestBody Coupon coupon){
        return service.deleteCoupon(coupon);
    }

    //更新优惠券
    @RequestMapping("/coupon/create")
    public TResultVo createCoupon(@RequestBody Coupon coupon){
        return service.createCoupon(coupon);
    }

    //id单个查询优惠券
    @RequestMapping("/coupon/read")
    public TResultVo queryCouponById(Coupon coupon){ return service.queryCouponById(coupon); }

    //查询优惠券用户信息
    @RequestMapping("/coupon/listuser")
    public MapResultVo queryCouponUser(PageUtil pageUtil, CouponAndUser couponAndUser){ return service.queryCouponUser(pageUtil, couponAndUser); }

    //查询专题
    @RequestMapping("/topic/list")
    public MapResultVo queryTopic(PageUtil pageUtil, Topic topic){ return service.queryTopic(pageUtil, topic); }

    //更新专题
    @RequestMapping("/topic/update")
    public TResultVo updateTopic(@RequestBody Topic topic){ return service.updateTopic(topic); }

    //删除专题
    @RequestMapping("/topic/delete")
    public TResultVo deleteTopic(@RequestBody Topic topic){ return service.deleteTopic(topic); }

    //添加专题
    @RequestMapping("/topic/create")
    public TResultVo createTopic(@RequestBody Topic topic){ return service.createTopic(topic); }

    //查询团购
    @RequestMapping("/groupon/listRecord")
    public MapResultVo queryGroupon(PageUtil pageUtil, Groupon groupon, GrouponRules rules){ return service.queryGroupon(pageUtil, groupon, rules); }

    //查询团购规则
    @RequestMapping("/groupon/list")
    public MapResultVo queryGrouponRules(PageUtil pageUtil, GrouponRules rules){
        return service.queryGrouponRules(pageUtil, rules);
    }

    //团购规则更新
    @RequestMapping("/groupon/update")
    public TResultVo updateGrouponRules(@RequestBody GrouponRules rules) throws CustomException {
        return service.updateGrouponRules(rules);
    }

    //团购规则删除
    @RequestMapping("/groupon/delete")
    public TResultVo deletedGrouponRules(@RequestBody GrouponRules rules){
        return service.deleteGrouponRules(rules);
    }

    //团购规则添加
    @RequestMapping("/groupon/create")
    public TResultVo createGrouponRules(@RequestBody GrouponRules rules) throws CustomException {
        return service.createGrouponRules(rules);
    }
}
