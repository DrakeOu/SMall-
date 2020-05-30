package com.xjtuse.mall.controller.wx;

import com.alibaba.druid.util.StringUtils;
import com.xjtuse.mall.annotation.LoginUser;
import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.goods.GoodsProduct;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.promotion.CouponAndUser;
import com.xjtuse.mall.bean.promotion.GrouponRules;
import com.xjtuse.mall.bean.user.LitemallAddress;
import com.xjtuse.mall.bean.user.Cart;
import com.xjtuse.mall.bean.user.LitemallAddress;
import com.xjtuse.mall.bean.user.User;
import com.xjtuse.mall.common.SystemConfig;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxGoodsService;
import com.xjtuse.mall.service.wx.WxOrderService;
import com.xjtuse.mall.service.wx.WxUserService;
import com.xjtuse.mall.token.TokenService;
import com.xjtuse.mall.utils.JsonUtil;
import com.xjtuse.mall.utils.ResultUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class WxCartController {

    private final Log logger = LogFactory.getLog(WxCartController.class);

    @Autowired
    TokenService tokenService;

    @Autowired
    WxUserService userService;

    @Autowired
    WxGoodsService goodsService;

    @Autowired
    WxOrderService orderService;

    @RequestMapping("/cart/index")
    public TResultVo CartInfo(@LoginUser Integer userId){
        //没有登录，返回登录错误，前端跳转登录
        if(null == userId){
            return ResultUtil.unlogin();
        }
        //查询user相关全部cart数据
        List<Cart> list =  userService.queryCartById(userId);

        List<Cart> cartList = new ArrayList<>();
        //清除全部过期的数据
        for(Cart cart: list){
            Goods goods = goodsService.queryGoodsById(cart.getGoodsId());
            if(goods == null || !goodsService.isOnSale(goods)){
                userService.deleteCartById(cart);
                logger.debug("清除过期商品");
            }else {
                cartList.add(cart);
            }
        }
        Integer goodsCount = 0;
        BigDecimal goodsAmount = new BigDecimal(0.00);
        Integer checkedGoodsCount = 0;
        BigDecimal checkedGoodsAmount = new BigDecimal(0.00);
        for (Cart cart : cartList) {
            goodsCount += cart.getNumber();
            goodsAmount = goodsAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            if (cart.getChecked()) {
                checkedGoodsCount += cart.getNumber();
                checkedGoodsAmount = checkedGoodsAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            }
        }
        Map<String, Object> cartTotal = new HashMap<>();
        cartTotal.put("goodsCount", goodsCount);
        cartTotal.put("goodsAmount", goodsAmount);
        cartTotal.put("checkedGoodsCount", checkedGoodsCount);
        cartTotal.put("checkedGoodsAmount", checkedGoodsAmount);

        Map<String, Object> result = new HashMap<>();
        result.put("cartList", cartList);
        result.put("cartTotal", cartTotal);

        return ResultUtil.genSuccessResult(result);
    }

    @RequestMapping("/cart/goodscount")
    public TResultVo goodsCount(@LoginUser Integer userId){
        if(userId==null){
            return ResultUtil.genSuccessResult(0);
        }
        List<Cart> list =  userService.queryCartById(userId);
        Integer goodsCount = 0;
        for(Cart cart: list){
            goodsCount += cart.getNumber();
        }
        return ResultUtil.genSuccessResult(goodsCount);
    }

    @RequestMapping("/cart/add")
    public TResultVo cartAdd(@LoginUser Integer userId, @RequestBody Cart cart){
        if(null == userId){
            return ResultUtil.unlogin();
        }
        if(cart == null){
            return ResultUtil.genFailResult("参数错误", 401);
        }

        Integer goodsId = cart.getGoodsId();
        Integer productId = cart.getProductId();
        Integer number = cart.getNumber().intValue();
        //检查商品是否可以购买
        Goods goods = goodsService.queryGoodsById(goodsId);
        if(goods == null||!goodsService.isOnSale(goods)){
            return ResultUtil.genFailResult("商品已下架");
        }
        //检查商品是否已在购物车中
        //goods中某种库存对应的product才是真正可以购买的东西
        GoodsProduct product = goodsService.queryProductByPid(productId);
        Cart existCart = userService.queryExistCart(goodsId, productId, userId);
        if(existCart==null){
            //判断库存类型是否充足
            if(product==null||product.getNumber()<number){
                return ResultUtil.genFailResult("库存不足!");
            }
            //设置购物车参数
            cart.setId(null);
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName((goods.getName()));
            if(StringUtils.isEmpty(product.getUrl())){
                cart.setPicUrl(goods.getPicUrl());
            }
            else{
                cart.setPicUrl(product.getUrl());
            }
            cart.setPrice(product.getPrice());
            cart.setSpecifications(product.getSpecifications());
            cart.setUserId(userId);
            cart.setChecked(true);
            userService.addCart(cart);
        }else{
            int num = number + existCart.getNumber();
            if(num > product.getNumber()){
                return ResultUtil.genFailResult("库存不足!");
            }
            existCart.setNumber((short) num);
            if(userService.updateCartById(existCart)==0){
                return ResultUtil.genFailResult("添加购物车失败!");
            }
        }
        return goodsCount(userId);
    }

    /**
     * 检查复选框
     * @param userId
     * @param body
     * @return
     */
    @RequestMapping("/cart/checked")
    public TResultVo cartChecked(@LoginUser Integer userId, @RequestBody String body){
        if(userId == null){
            return ResultUtil.unlogin();
        }
        if(body == null){
            return ResultUtil.genFailResult("参数错误");
        }
        List<Integer> productIds = JsonUtil.parseIntegerList(body, "productIds");
        if (productIds == null) {
            return ResultUtil.genFailResult("参数错误");
        }

        Integer checkValue = JsonUtil.parseInteger(body, "isChecked");
        if (checkValue == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        Boolean isChecked = (checkValue == 1);

        userService.updateCheck(userId, productIds, isChecked);
        return CartInfo(userId);

    }

    /**
     * 下单
     * @param userId
     * @param cartId
     * @param addressId
     * @param couponId
     * @param userCouponId
     * @param grouponRulesId
     * @return
     */
    @RequestMapping("/cart/checkout")
    public TResultVo cartCheckout(@LoginUser Integer userId, Integer cartId, Integer addressId, Integer couponId, Integer userCouponId, Integer grouponRulesId){
        if(userId==null){
            return ResultUtil.unlogin();
        }
        // 收货地址
        LitemallAddress checkedAddress = null;
        if (addressId == null || addressId.equals(0)) {
            checkedAddress = userService.findDefaultAddress(userId);
            // 如果仍然没有地址，则是没有收货地址
            // 返回一个空的地址id=0，这样前端则会提醒添加地址
            if (checkedAddress == null) {
                checkedAddress = new LitemallAddress();
                checkedAddress.setId(0);
                addressId = 0;
            } else {
                addressId = checkedAddress.getId();
            }

        } else {
            checkedAddress = userService.queryAddress(userId, addressId);
            // 如果null, 则报错
            if (checkedAddress == null) {
                return ResultUtil.genFailResult("参数错误");
            }
        }

        // 团购优惠
        BigDecimal grouponPrice = new BigDecimal(0.00);
        GrouponRules grouponRules = orderService.queryGrouponRulesById(grouponRulesId);
        if (grouponRules != null) {
            grouponPrice = grouponRules.getDiscount();
        }

        // 商品价格,可能是点击单个商品下单，把购物车中商品也加入计算
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
        BigDecimal tmpCouponPrice = new BigDecimal(0.00);
        Integer tmpCouponId = 0;
        Integer tmpUserCouponId = 0;
        int tmpCouponLength = 0;
        List<CouponAndUser> couponUserList = userService.queryAllCouponAndUser(userId);
        for(CouponAndUser couponUser : couponUserList){
            Coupon coupon = userService.checkCoupon(userId, couponUser.getCouponId(), couponUser.getId(), checkedGoodsPrice);
            if(coupon == null){
                continue;
            }

            tmpCouponLength++;
            if(tmpCouponPrice.compareTo(coupon.getDiscount()) == -1){
                tmpCouponPrice = coupon.getDiscount();
                tmpCouponId = coupon.getId();
                tmpUserCouponId = couponUser.getId();
            }
        }
        // 获取优惠券减免金额，优惠券可用数量
        int availableCouponLength = tmpCouponLength;
        BigDecimal couponPrice = new BigDecimal(0);
        // 这里存在三种情况
        // 1. 用户不想使用优惠券，则不处理
        // 2. 用户想自动使用优惠券，则选择合适优惠券
        // 3. 用户已选择优惠券，则测试优惠券是否合适
        if (couponId == null || couponId.equals(-1)){
            couponId = -1;
            userCouponId = -1;
        }
        else if (couponId.equals(0)) {
            couponPrice = tmpCouponPrice;
            couponId = tmpCouponId;
            userCouponId = tmpUserCouponId;
        }
        else {
            Coupon coupon = userService.checkCoupon(userId, couponId, userCouponId, checkedGoodsPrice);
            // 用户选择的优惠券有问题，则选择合适优惠券，否则使用用户选择的优惠券
            if(coupon == null){
                couponPrice = tmpCouponPrice;
                couponId = tmpCouponId;
                userCouponId = tmpUserCouponId;
            }
            else {
                couponPrice = coupon.getDiscount();
            }
        }

        // 根据订单商品总价计算运费，满88则免运费，否则8元；
        BigDecimal freightPrice = new BigDecimal(0.00);
        if (checkedGoodsPrice.compareTo(new BigDecimal(88.0)) < 0) {
            freightPrice = new BigDecimal(8.0);
        }

        // 可以使用的其他钱，例如用户积分
        BigDecimal integralPrice = new BigDecimal(0.00);

        // 订单费用
        BigDecimal orderTotalPrice = checkedGoodsPrice.add(freightPrice).subtract(couponPrice).max(new BigDecimal(0.00));

        BigDecimal actualPrice = orderTotalPrice.subtract(integralPrice);

        Map<String, Object> data = new HashMap<>();
        data.put("addressId", addressId);
        data.put("couponId", couponId);
        data.put("userCouponId", userCouponId);
        data.put("cartId", cartId);
        data.put("grouponRulesId", grouponRulesId);
        data.put("grouponPrice", grouponPrice);
        data.put("checkedAddress", checkedAddress);
        data.put("availableCouponLength", availableCouponLength);
        data.put("goodsTotalPrice", checkedGoodsPrice);
        data.put("freightPrice", freightPrice);
        data.put("couponPrice", couponPrice);
        data.put("orderTotalPrice", orderTotalPrice);
        data.put("actualPrice", actualPrice);
        data.put("checkedGoodsList", checkedGoodsList);
        return ResultUtil.genSuccessResult(data);
    }

    /**
     * 删除购物车
     * @param userId
     * @param body
     * @return
     */
    @RequestMapping("/cart/delete")
    public TResultVo cartDelete(@LoginUser Integer userId, @RequestBody String body){
        if(userId==null){
            return ResultUtil.unlogin();
        }
        if(body==null){
            return ResultUtil.genFailResult("参数错误");
        }
        List<Integer> productIds = JsonUtil.parseIntegerList(body, "productIds");

        if (productIds == null || productIds.size() == 0) {
            return ResultUtil.genFailResult("参数错误");
        }

        userService.deleteCartList(productIds, userId);

        return CartInfo(userId);
    }

    /**
     * 立即购买，添加到购物车中再购买
     * @param userId
     * @param cart
     * @return
     */
    @RequestMapping("/cart/fastadd")
    public TResultVo cartFastadd(@LoginUser Integer userId, @RequestBody Cart cart){
        if(userId==null){
            return ResultUtil.unlogin();
        }
        if(cart==null){
            return ResultUtil.genFailResult("参数错误");
        }

        Integer productId = cart.getProductId();
        Integer number = cart.getNumber().intValue();
        Integer goodsId = cart.getGoodsId();
        if(null==productId||null==goodsId||number<=0){
            return ResultUtil.genFailResult("参数错误");
        }

        //检查商品是否可以购买
        Goods goods = goodsService.queryGoodsById(goodsId);
        if(goods == null||!goodsService.isOnSale(goods)){
            return ResultUtil.genFailResult("商品已下架");
        }
        //取得规格的信息,判断规格库存
        GoodsProduct product = goodsService.queryProductByPid(productId);
        if (product == null || product.getNumber() < number) {
            return ResultUtil.genFailResult("库存不足");
        }
        Cart existCart = userService.queryExistCart(goodsId, productId, userId);
        if(existCart==null){
            //判断库存类型是否充足
            if(product==null||product.getNumber()<number){
                return ResultUtil.genFailResult("库存不足!");
            }
            //设置购物车参数
            cart.setId(null);
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName((goods.getName()));
            if(StringUtils.isEmpty(product.getUrl())){
                cart.setPicUrl(goods.getPicUrl());
            }
            else{
                cart.setPicUrl(product.getUrl());
            }
            cart.setPrice(product.getPrice());
            cart.setSpecifications(product.getSpecifications());
            cart.setUserId(userId);
            cart.setChecked(true);
            userService.addCart(cart);
        }else{
            int num = number + existCart.getNumber();
            if(num > product.getNumber()){
                return ResultUtil.genFailResult("库存不足!");
            }
            existCart.setNumber((short) num);
            if(userService.updateCartById(existCart)==0){
                return ResultUtil.genFailResult("添加购物车失败!");
            }
        }
        return ResultUtil.genSuccessResult(existCart != null ? existCart.getId() : cart.getId());
    }

    @RequestMapping("/cart/update")
    public TResultVo cartUpdate(@LoginUser Integer userId, @RequestBody Cart cart){
        if(userId==null){
            return ResultUtil.unlogin();
        }
        Integer productId = cart.getProductId();
        Integer number = cart.getNumber().intValue();
        Integer goodsId = cart.getGoodsId();
        Integer id = cart.getId();
        if(null==productId||null==goodsId||null==id||number<=0){
            return ResultUtil.genFailResult("参数错误");
        }

        Cart existCart = userService.queryExistCart(goodsId, productId, userId);
        if(existCart==null){
            return ResultUtil.genFailResult("参数错误");
        }

        if (!existCart.getGoodsId().equals(goodsId)) {
            return ResultUtil.genFailResult("参数错误");
        }
        if (!existCart.getProductId().equals(productId)) {
            return ResultUtil.genFailResult("参数错误");
        }

        //检查商品是否可以购买
        Goods goods = goodsService.queryGoodsById(goodsId);
        if(goods == null||!goodsService.isOnSale(goods)){
            return ResultUtil.genFailResult("商品已下架");
        }

        //取得规格的信息,判断规格库存
        GoodsProduct product = goodsService.queryProductByPid(productId);
        if (product == null || product.getNumber() < number) {
            return ResultUtil.genFailResult("库存不足");
        }
        existCart.setNumber(number.shortValue());
        if (userService.updateCartById(existCart) == 0) {
            return ResultUtil.genFailResult("添加购物车失败");
        }
        return ResultUtil.genSuccessResult();


    }

}
