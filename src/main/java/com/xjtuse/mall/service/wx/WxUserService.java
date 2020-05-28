package com.xjtuse.mall.service.wx;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.user.Cart;

import java.util.List;

public interface WxUserService {
    List<Coupon> queryAllCoupon();

    Integer queryCollectCount(Goods goods);

    List<Cart> queryCartById(Integer userId);

    void deleteCartById(Cart cart);
}
