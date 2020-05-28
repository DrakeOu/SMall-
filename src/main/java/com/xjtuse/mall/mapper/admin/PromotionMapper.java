package com.xjtuse.mall.mapper.admin;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.promotion.*;
import com.xjtuse.mall.utils.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PromotionMapper {

    List<Ad> queryAd(@Param("pageUtil") PageUtil pageUtil, @Param("ad") Ad ad);
    int queryAdCount(@Param("pageUtil") PageUtil pageUtil, @Param("ad") Ad ad);

    void updateAd(Ad ad);

    void deleteAd(Ad ad);

    void createAd(Ad ad);

    List<Coupon> queryCoupon(@Param("pageUtil") PageUtil pageUtil, @Param("coupon") Coupon coupon);
    int queryCouponCount(@Param("pageUtil") PageUtil pageUtil, @Param("coupon") Coupon coupon);

    void updateCoupon(Coupon coupon);

    void deleteCoupon(Coupon coupon);

    void createCoupon(Coupon coupon);

    Coupon queryCouponById(Coupon coupon);

    List<CouponAndUser> queryCouponUser(@Param("pageUtil") PageUtil pageUtil, @Param("couponUser") CouponAndUser couponUser);
    int queryCouponUserCount(@Param("pageUtil") PageUtil pageUtil, @Param("couponUser") CouponAndUser couponUser);

    List<Topic> queryTopic(@Param("pageUtil") PageUtil pageUtil, @Param("topic") Topic topic);
    int queryTopicCount(@Param("pageUtil") PageUtil pageUtil, @Param("topic") Topic topic);

    void updateTopic(Topic topic);

    void deleteTopic(Topic topic);

    void createTopic(Topic topic);

    List<Integer> queryGrouponIdDistinct(@Param("pageUtil") PageUtil pageUtil, @Param("groupon") Groupon groupon);
    int queryGrouponCount(@Param("pageUtil") PageUtil pageUtil, @Param("groupon") Groupon groupon);

    List<Groupon> queryGrouponByGid(Integer grouponId);

    GrouponRules queryRelativeRules(Integer rulesId);

    Integer queryRulesIdByGoodsId(GrouponRules rules);

    List<GrouponRules> queryGrouponRules(@Param("pageUtil") PageUtil pageUtil, @Param("rules") GrouponRules rules);
    int queryGrouponRulesCount(@Param("pageUtil") PageUtil pageUtil, @Param("rules") GrouponRules rules);

    void updateGrouponRules(GrouponRules rules);

    void deleteGrouponRules(GrouponRules rules);

    void createGrouponRules(GrouponRules rules);
    Goods queryGoodsById(GrouponRules rules);

}
