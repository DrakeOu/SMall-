package com.xjtuse.mall.mapper.wx;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.promotion.CouponAndUser;
import com.xjtuse.mall.bean.user.Cart;
import com.xjtuse.mall.bean.user.Collect;
import com.xjtuse.mall.utils.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxUserMapper {
    List<Coupon> queryAllCoupon();

    Integer queryCollectCount(Goods goods);

    List<Cart> queryCartById(Integer userId);

    void deleteCartById(Cart cart);

    Cart queryExistCart(@Param("goodsId") Integer goodsId, @Param("productId") Integer productId, @Param("userId") Integer userId);

    void addCart(Cart cart);

    int updateCartById(Cart existCart);

    void updateCheck(@Param("userId") Integer userId, @Param("productId") Integer productId, @Param("isChecked") Boolean isChecked);

    Coupon queryCouponById(Integer couponId);

    Integer countCoupon(Integer couponId);

    Integer countUserAndCoupon(@Param("userId") Integer userId, @Param("couponId") Integer couponId);

    void addCouponUser(CouponAndUser couponUser);

    Collect queryCollectByTypeAndValue(Integer userId, Byte type, Integer valueId);

    void deleteCollectById(Collect collect);

    void addCollect(Collect newCollect);

    List<Collect> queryCollectList(Integer userId, PageUtil pageUtil, Integer type);

    /**
     * 此mapper负责控制如下 表数据
     * litemall_coupon
     * litemall_coupon_user
     * litemall_cart
     * litemall_feedback
     * litemall_address
     * litemall_comment
     * litemall_footpaint
     * litemall_user
     * litemall_collect
     * litemall_search_history
     *
     */

}
