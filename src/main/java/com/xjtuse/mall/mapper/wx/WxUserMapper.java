package com.xjtuse.mall.mapper.wx;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.promotion.Coupon;

import java.util.List;

public interface WxUserMapper {
    List<Coupon> queryAllCoupon();

    Integer queryCollectCount(Goods goods);

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
