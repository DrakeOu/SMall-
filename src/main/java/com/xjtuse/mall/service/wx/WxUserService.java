package com.xjtuse.mall.service.wx;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.promotion.Coupon;

import java.util.List;

public interface WxUserService {
    List<Coupon> queryAllCoupon();

    Integer queryCollectCount(Goods goods);
}
