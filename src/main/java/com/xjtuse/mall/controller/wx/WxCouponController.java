package com.xjtuse.mall.controller.wx;

import com.xjtuse.mall.VO.CouponVo;
import com.xjtuse.mall.annotation.LoginUser;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.promotion.CouponAndUser;
import com.xjtuse.mall.bean.promotion.GrouponRules;
import com.xjtuse.mall.bean.user.Cart;
import com.xjtuse.mall.common.CouponConstant;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxOrderService;
import com.xjtuse.mall.service.wx.WxUserService;
import com.xjtuse.mall.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wx")
public class WxCouponController {


    @Autowired
    WxUserService userService;

    @Autowired
    WxOrderService orderService;

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


    /**
     * 优惠券列表
     */
    @GetMapping("/coupon/list")
    public Object list(PageUtil pageUtil) {
        List<Coupon> couponList = userService.queryCouponList(pageUtil);
        Integer count = userService.queryAllCoupon().size();
        //计算pages
        Integer pages = 1 + count / pageUtil.getLimit();
        return ResultUtil.genSuccessResult(VoUtil.genListVo(
                count,
                pages,
                pageUtil.getLimit(),
                pageUtil.getPage(),
                couponList
        ));
    }

    /**
     * 个人优惠券列表
     * @return
     */
    @GetMapping("/coupon/mylist")
    public Object mylist(@LoginUser Integer userId,
                         Short status,
                         PageUtil pageUtil) {
        if(userId == null){
            return ResultUtil.unlogin();
        }

        List<CouponAndUser> couponUserList = userService.queryCouponAndUserList(userId, status, pageUtil);
        List<CouponVo> couponVoList = change(couponUserList);
        Integer count = couponUserList.size();
        //计算pages
        Integer pages = 1 + count / pageUtil.getLimit();
        return ResultUtil.genSuccessResult(VoUtil.genListVo(
                count,
                pages,
                pageUtil.getLimit(),
                pageUtil.getPage(),
                couponVoList
        ));
    }

    private List<CouponVo> change(List<CouponAndUser> couponList) {
        List<CouponVo> couponVoList = new ArrayList<>(couponList.size());
        for(CouponAndUser couponUser : couponList){
            Integer couponId = couponUser.getCouponId();
            Coupon coupon = userService.queryCouponById(couponId);
            CouponVo couponVo = new CouponVo();
            couponVo.setId(couponUser.getId());
            couponVo.setCid(coupon.getId());
            couponVo.setName(coupon.getName());
            couponVo.setDesc(coupon.getDesc());
            couponVo.setTag(coupon.getTag());
            couponVo.setMin(coupon.getMin());
            couponVo.setDiscount(coupon.getDiscount());
            couponVo.setStartTime(couponUser.getStartTime());
            couponVo.setEndTime(couponUser.getEndTime());

            couponVoList.add(couponVo);
        }
        return couponVoList;
    }


    /**
     * 当前购物车下单商品订单可用优惠券
     *
     * @param userId
     * @param cartId
     * @param grouponRulesId
     * @return
     */
    @GetMapping("/coupon/selectlist")
    public Object selectlist(@LoginUser Integer userId, Integer cartId, Integer grouponRulesId) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }

        // 团购优惠
        BigDecimal grouponPrice = new BigDecimal(0.00);
        GrouponRules grouponRules = orderService.queryGrouponRulesById(grouponRulesId);
        if (grouponRules != null) {
            grouponPrice = grouponRules.getDiscount();
        }

        // 商品价格
        List<Cart> checkedGoodsList = null;
        if (cartId == null || cartId.equals(0)) {
            checkedGoodsList = userService.queryCartByUidAndChecked(userId);
        } else {
            Cart cart = userService.queryCartByCid(userId, cartId);
            if (cart == null) {
                return ResultUtil.genFailResult("参数错误");
            }
            checkedGoodsList = new ArrayList<>(1);
            checkedGoodsList.add(cart);
        }
        BigDecimal checkedGoodsPrice = new BigDecimal(0.00);
        for (Cart cart : checkedGoodsList) {
            //  只有当团购规格商品ID符合才进行团购优惠
            if (grouponRules != null && grouponRules.getGoodsId().equals(cart.getGoodsId())) {
                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().subtract(grouponPrice).multiply(new BigDecimal(cart.getNumber())));
            } else {
                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            }
        }

        // 计算优惠券可用情况
        List<CouponAndUser> couponUserList = userService.queryAllCouponAndUser(userId);
        List<CouponVo> couponVoList = change(couponUserList);
        for (CouponVo cv : couponVoList) {
            Coupon coupon = userService.checkCoupon(userId, cv.getCid(), cv.getId(), checkedGoodsPrice);
            cv.setAvailable(coupon != null);
        }
        //计算count pages
        Integer count = couponVoList.size();
        Integer pages = 2;

        return ResultUtil.genSuccessResult(VoUtil.genListVo(
                count,
                pages,
                count,
                1,
                couponVoList
        ));
    }

    /**
     * 优惠券兑换
     *
     * @param userId 用户ID
     * @param body 请求内容， { code: xxx }
     * @return 操作结果
     */
    @PostMapping("/coupon/exchange")
    public Object exchange(@LoginUser Integer userId, @RequestBody String body) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }

        String code = JsonUtil.parseString(body, "code");
        if(code == null){
            return ResultUtil.genFailResult("参数错误");
        }

        Coupon coupon = userService.queryCouponByCode(code);
        if(coupon == null){
            return ResultUtil.genFailResult("优惠券不正确");
        }
        Integer couponId = coupon.getId();

        // 当前已领取数量和总数量比较
        Integer total = coupon.getTotal();
        Integer totalCoupons = userService.countCoupon(couponId);
        if((total != 0) && (totalCoupons >= total)){
            return ResultUtil.genFailResult("优惠券已兑换");
        }

        // 当前用户已领取数量和用户限领数量比较
        Integer limit = coupon.getLimit().intValue();
        Integer userCounpons = userService.countUserAndCoupon(userId, couponId);
        if((limit != 0) && (userCounpons >= limit)){
            return ResultUtil.genFailResult("优惠券已兑换");
        }

        // 优惠券分发类型
        // 例如注册赠券类型的优惠券不能领取
        Short type = coupon.getType().shortValue();
        if(type.equals(CouponConstant.TYPE_REGISTER)){
            return ResultUtil.genFailResult("新用户优惠券自动发送");
        }
        else if(type.equals(CouponConstant.TYPE_COMMON)){
            return ResultUtil.genFailResult("优惠券只能领取，不能兑换");
        }
        else if(!type.equals(CouponConstant.TYPE_CODE)){
            return ResultUtil.genFailResult("优惠券类型不支持");
        }

        // 优惠券状态，已下架或者过期不能领取
        Short status = coupon.getStatus().shortValue();
        if(status.equals(CouponConstant.STATUS_OUT)){
            return ResultUtil.genFailResult("优惠券已兑换");
        }
        else if(status.equals(CouponConstant.STATUS_EXPIRED)){
            return ResultUtil.genFailResult("优惠券已经过期");
        }

        // 用户领券记录
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
