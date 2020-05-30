package com.xjtuse.mall.service.wx.impl;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.promotion.CouponAndUser;
import com.xjtuse.mall.bean.user.Address;
import com.xjtuse.mall.bean.user.Cart;
import com.xjtuse.mall.bean.user.Collect;
import com.xjtuse.mall.bean.user.LitemallAddress;
import com.xjtuse.mall.common.CouponConstant;
import com.xjtuse.mall.mapper.wx.WxUserMapper;
import com.xjtuse.mall.service.wx.WxUserService;
import com.xjtuse.mall.utils.DateUtil;
import com.xjtuse.mall.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    WxUserMapper userMapper;

    @Override
    public List<Coupon> queryAllCoupon() {
        return userMapper.queryAllCoupon();
    }

    @Override
    public Integer queryCollectCount(Goods goods) {
        return userMapper.queryCollectCount(goods);
    }

    @Override
    public List<Cart> queryCartById(Integer userId) {
        return userMapper.queryCartById(userId);
    }

    @Override
    public void deleteCartById(Cart cart) {
        userMapper.deleteCartById(cart);
    }

    @Override
    public Cart queryExistCart(Integer goodsId, Integer productId, Integer userId) {
        return userMapper.queryExistCart(goodsId, productId, userId);
    }

    @Override
    public void addCart(Cart cart) {
        userMapper.addCart(cart);
    }

    @Override
    public int updateCartById(Cart existCart) {
        return userMapper.updateCartById(existCart);
    }

    @Override
    public void updateCheck(Integer userId, List<Integer> productIds, Boolean isChecked) {
        for (Integer productId :
                productIds) {
            userMapper.updateCheck(userId, productId, isChecked);
        }
    }

    @Override
    public Coupon queryCouponById(Integer couponId) {
        return userMapper.queryCouponById(couponId);
    }

    @Override
    public Integer countCoupon(Integer couponId) {
        return userMapper.countCoupon(couponId);
    }

    @Override
    public Integer countUserAndCoupon(Integer userId, Integer couponId) {
        return userMapper.countUserAndCoupon(userId, couponId);
    }

    @Override
    public void addCouponUser(CouponAndUser couponUser) {
        userMapper.addCouponUser(couponUser);
    }

    @Override
    public Collect queryCollectByTypeAndValue(Integer userId, Byte type, Integer valueId) {
        return userMapper.queryCollectByTypeAndValue(userId, type, valueId);
    }

    @Override
    public void deleteCollectById(Collect collect) {
        userMapper.deleteCollectById(collect);
    }

    @Override
    public void addCollect(Collect newCollect) {
        userMapper.addCollect(newCollect);
    }

    @Override
    public List<Collect> queryCollectList(Integer userId, PageUtil pageUtil, Integer type) {
        return userMapper.queryCollectList(userId, pageUtil, type);
    }

    @Override
    public void deleteCartList(List<Integer> productIds, Integer userId) {
        for (Integer productId :
                productIds) {
            userMapper.deleteCartByPro(productId, userId);
        }
    }

    @Override
    public LitemallAddress findDefaultAddress(Integer userId) {
        return userMapper.findDefaultAddress(userId);
    }

    @Override
    public LitemallAddress queryAddress(Integer userId, Integer addressId) {
        return userMapper.queryAddress(userId, addressId);
    }

    @Override
    public List<Cart> queryCartByUidAndChecked(Integer userId) {
        return userMapper.queryCartByUidAndChecked(userId);
    }

    @Override
    public Cart queryCartByCid(Integer userId, Integer cartId) {
        return userMapper.queryCartByCid(userId, cartId);
    }

    @Override
    public List<CouponAndUser> queryAllCouponAndUser(Integer userId) {
        return userMapper.queryAllCouponAndUser(userId);
    }

    @Override
    public Coupon checkCoupon(Integer userId, Integer couponId, Integer userCouponId, BigDecimal checkedGoodsPrice) {
        Coupon coupon = queryCouponById(couponId);
        if (coupon == null) {
            return null;
        } else {
            CouponAndUser couponUser = queryCouponAndUserById(userCouponId);
            if (couponUser == null) {
                couponUser = queryOneCouponUser(userId, couponId);
            } else if (!couponId.equals(couponUser.getCouponId())) {
                return null;
            }

            if (couponUser == null) {
                return null;
            } else {
                Short timeType = coupon.getTimeType().shortValue();
                Short days = coupon.getDays().shortValue();
                LocalDateTime now = LocalDateTime.now();
                if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
                    if (now.isBefore(DateUtil.asLocalDateTime(coupon.getStartTime())) || now.isAfter(DateUtil.asLocalDateTime(coupon.getEndTime()))) {
                        return null;
                    }
                } else {
                    if (!timeType.equals(CouponConstant.TIME_TYPE_DAYS)) {
                        return null;
                    }

                    LocalDateTime expired = couponUser.getAddTime().plusDays((long)days);
                    if (now.isAfter(expired)) {
                        return null;
                    }
                }

                Short goodType = coupon.getGoodsType().shortValue();
                if (!goodType.equals(CouponConstant.GOODS_TYPE_ALL)) {
                    return null;
                } else {
                    Short status = coupon.getStatus().shortValue();
                    if (!status.equals(CouponConstant.STATUS_NORMAL)) {
                        return null;
                    } else {
                        return checkedGoodsPrice.compareTo(coupon.getMin()) == -1 ? null : coupon;
                    }
                }
            }
        }
    }

    @Override
    public CouponAndUser queryOneCouponUser(Integer userId, Integer couponId) {
        List<CouponAndUser> couponAndUserList = queryAllCouponAndUser(userId);
        for(CouponAndUser c: couponAndUserList){
            if(c.getCouponId().equals(couponId)){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Coupon> queryCouponList(PageUtil pageUtil) {
        return userMapper.queryCouponList(pageUtil);
    }

    @Override
    public List<CouponAndUser> queryCouponAndUserList(Integer userId, Short status, PageUtil pageUtil) {
        return userMapper.queryCouponAndUserList(userId, status, pageUtil);
    }

    @Override
    public Coupon queryCouponByCode(String code) {
        return userMapper.queryCouponByCode(code);
    }

    @Override
    public List<LitemallAddress> queryAddressListByUid(Integer userId) {
        return userMapper.queryAddressListByUid(userId);
    }

    @Override
    public void resetDefaultAddress(Integer userId) {
        userMapper.resetDefaultAddress(userId);
    }

    @Override
    public void addAddress(LitemallAddress address) {
        userMapper.addAddress(address);
    }

    @Override
    public void updateAddress(LitemallAddress address) {
        userMapper.updateAddress(address);
    }

    @Override
    public void deleteAddressById(Integer id) {
        userMapper.deleteAddressById(id);
    }

    @Override
    public CouponAndUser queryCouponAndUserById(Integer userCouponId) {
        return userMapper.queryCouponAndUserById(userCouponId);
    }
}
