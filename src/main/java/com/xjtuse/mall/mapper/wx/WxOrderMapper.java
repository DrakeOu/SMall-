package com.xjtuse.mall.mapper.wx;

import com.xjtuse.mall.bean.mall.Order;
import com.xjtuse.mall.bean.promotion.Groupon;

import java.util.List;

public interface WxOrderMapper {
    List<Groupon> queryTwoGroupon();

    List<Order> queryOrderList(Integer userId);
    /**
     * 此mapper及对应service 控制 如下 表数据
     * order
     * aftersale
     * groupop
     * groupon_rules
     */

}
