package com.xjtuse.mall.service.wx;

import com.xjtuse.mall.bean.promotion.Groupon;

import java.util.List;

public interface WxOrderService {
    List<Groupon> queryTwoGroupon();

    Object orderInfo(Integer userId);
}
