package com.xjtuse.mall.service.wx;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.promotion.CouponAndUser;
import com.xjtuse.mall.bean.user.Address;
import com.xjtuse.mall.bean.user.Cart;
import com.xjtuse.mall.bean.user.Collect;
import com.xjtuse.mall.bean.user.LitemallAddress;
import com.xjtuse.mall.utils.PageUtil;

import java.math.BigDecimal;
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

    void deleteCartList(List<Integer> productIds, Integer userId);

    LitemallAddress findDefaultAddress(Integer userId);

    LitemallAddress queryAddress(Integer userId, Integer addressId);

    List<Cart> queryCartByUidAndChecked(Integer userId);

    Cart queryCartByCid(Integer userId, Integer cartId);

    List<CouponAndUser> queryAllCouponAndUser(Integer userId);

    Coupon checkCoupon(Integer userId, Integer couponId, Integer userCouponId, BigDecimal checkedGoodsPrice);

    CouponAndUser queryCouponAndUserById(Integer userCouponId);

    CouponAndUser queryOneCouponUser(Integer userId, Integer couponId);

    List<Coupon> queryCouponList(PageUtil pageUtil);

    List<CouponAndUser> queryCouponAndUserList(Integer userId, Short status, PageUtil pageUtil);

    Coupon queryCouponByCode(String code);

    List<LitemallAddress> queryAddressListByUid(Integer userId);

    void resetDefaultAddress(Integer userId);

    void addAddress(LitemallAddress address);

    void updateAddress(LitemallAddress address);

    void deleteAddressById(Integer id);
}
