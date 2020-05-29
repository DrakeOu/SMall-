package com.xjtuse.mall.service.wx;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.promotion.CouponAndUser;
import com.xjtuse.mall.bean.user.Cart;
import com.xjtuse.mall.bean.user.Collect;
import com.xjtuse.mall.utils.PageUtil;

import java.util.List;

public interface WxUserService {
    List<Coupon> queryAllCoupon();

    Integer queryCollectCount(Goods goods);

    List<Cart> queryCartById(Integer userId);

    void deleteCartById(Cart cart);

    Cart queryExistCart(Integer goodsId, Integer productId, Integer userId);

    void addCart(Cart cart);

    int updateCartById(Cart existCart);

    void updateCheck(Integer userId, List<Integer> productIds, Boolean isChecked);

    Coupon queryCouponById(Integer couponId);

    Integer countCoupon(Integer couponId);

    Integer countUserAndCoupon(Integer userId, Integer couponId);

    void addCouponUser(CouponAndUser couponUser);

    Collect queryCollectByTypeAndValue(Integer userId, Byte type, Integer valueId);

    void deleteCollectById(Collect collect);

    void addCollect(Collect newCollect);

    List<Collect> queryCollectList(Integer userId, PageUtil pageUtil, Integer type);
}
