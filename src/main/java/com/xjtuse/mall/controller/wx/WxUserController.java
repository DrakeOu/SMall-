package com.xjtuse.mall.controller.wx;


import com.xjtuse.mall.annotation.LoginUser;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.promotion.CouponAndUser;
import com.xjtuse.mall.common.CouponConstant;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxOrderService;
import com.xjtuse.mall.service.wx.WxUserService;
import com.xjtuse.mall.utils.DateUtil;
import com.xjtuse.mall.utils.JsonUtil;
import com.xjtuse.mall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class WxUserController {

    @Autowired
    WxOrderService orderService;

    @Autowired
    WxUserService userService;

    @RequestMapping("/user/index")
    public TResultVo userIndex(@LoginUser Integer userId){
        if(userId == null){
            return ResultUtil.unlogin();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("order", orderService.orderInfo(userId));

        return ResultUtil.genSuccessResult(data);
    }

    @RequestMapping("/coupon/receive")
    public TResultVo couponReceive(@LoginUser Integer userId, @RequestBody String body){
        if(userId == null){
            return ResultUtil.unlogin();
        }
        Integer couponId = JsonUtil.parseInteger(body, "couponId");
        if(couponId == null){
            return ResultUtil.genFailResult("参数错误");
        }

        Coupon coupon = userService.queryCouponById(couponId);
        //已领数量(发放数量)和总数比较
        Integer total = coupon.getTotal();
        Integer totalCoupons = userService.countCoupon(couponId);
        if((total != 0) && (totalCoupons >= total)){
            return ResultUtil.genFailResult("优惠券已领完");
        }
        //已领数量和用户限领比较
        Integer limit = coupon.getLimit().intValue();
        Integer userCounpons = userService.countUserAndCoupon(userId, couponId);
        if((limit != 0) && (userCounpons >= limit)){
            return ResultUtil.genFailResult("优惠券已领过");
        }

        //按分发类型进行检查
        // 例如注册赠券类型的优惠券不能领取
        Short type = coupon.getType().shortValue();
        if(type.equals(CouponConstant.TYPE_REGISTER)){
            return ResultUtil.genFailResult("新用户优惠券自动发送");
        }
        else if(type.equals(CouponConstant.TYPE_CODE)){
            return ResultUtil.genFailResult("优惠券只能兑换");
        }
        else if(!type.equals(CouponConstant.TYPE_COMMON)){
            return ResultUtil.genFailResult("优惠券类型不支持");
        }

        // 优惠券状态，已下架或者过期不能领取
        Short status = coupon.getStatus().shortValue();
        if(status.equals(CouponConstant.STATUS_OUT)){
            return ResultUtil.genFailResult("优惠券已领完");
        }
        else if(status.equals(CouponConstant.STATUS_EXPIRED)){
            return ResultUtil.genFailResult("优惠券已经过期");
        }

        CouponAndUser couponUser = new CouponAndUser();
        couponUser.setCouponId(couponId);
        couponUser.setUserId(userId);
        Short timeType = coupon.getTimeType().shortValue();
        if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
            couponUser.setStartTime(DateUtil.asLocalDateTime(coupon.getStartTime()));
            couponUser.setEndTime(DateUtil.asLocalDateTime(coupon.getEndTime()));
        }
        else{
            LocalDateTime now = LocalDateTime.now();
            couponUser.setStartTime(now);
            couponUser.setEndTime(now.plusDays(coupon.getDays()));
        }
        userService.addCouponUser(couponUser);

        return ResultUtil.genSuccessResult();
    }
}
