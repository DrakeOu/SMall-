package com.xjtuse.mall.service.wx.impl;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.user.Cart;
import com.xjtuse.mall.mapper.wx.WxUserMapper;
import com.xjtuse.mall.service.wx.WxUserService;
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
}
