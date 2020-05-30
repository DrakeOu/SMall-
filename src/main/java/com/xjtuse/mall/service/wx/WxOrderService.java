package com.xjtuse.mall.service.wx;

import com.xjtuse.mall.bean.promotion.Groupon;
import com.xjtuse.mall.bean.promotion.GrouponRules;

import java.util.List;

public interface WxOrderService {
    List<Groupon> queryTwoGroupon();

    Object orderInfo(Integer userId);

    GrouponRules queryGrouponRulesById(Integer grouponRulesId);
}
