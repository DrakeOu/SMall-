package com.xjtuse.mall.service.wx.impl;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.promotion.CouponAndUser;
import com.xjtuse.mall.bean.user.Cart;
import com.xjtuse.mall.bean.user.Collect;
import com.xjtuse.mall.mapper.wx.WxUserMapper;
import com.xjtuse.mall.service.wx.WxUserService;
import com.xjtuse.mall.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
