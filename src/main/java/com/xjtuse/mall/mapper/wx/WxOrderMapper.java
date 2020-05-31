package com.xjtuse.mall.mapper.wx;

import com.xjtuse.mall.bean.mall.Order;
import com.xjtuse.mall.bean.promotion.Groupon;
import com.xjtuse.mall.bean.promotion.GrouponRules;
import com.xjtuse.mall.utils.PageUtil;

import java.util.List;

public interface WxOrderMapper {
    List<Groupon> queryTwoGroupon();

    List<Order> queryOrderList(Integer userId);

    GrouponRules queryGrouponRulesById(Integer grouponRulesId);

    Order queryOrderById(Integer userId, Integer orderId);

    int countGroupon(Integer grouponLinkId);

    boolean hasJoinGroupon(Integer userId, Integer grouponLinkId);

    Groupon queryGrouponById(Integer userId, Integer grouponLinkId);

    void addOrder(Order order);

    void createGroupon(Groupon groupon);

    int updateOrder(Order order);

    void deleteOrderById(Integer orderId);

    void deleteAftersaleByOrderId(Integer userId, Integer orderId);

    Order queryOrderByOsn(String orderSn);

    List<Order> queryByOrderStatus(Integer userId, Short status, PageUtil pageUtil);

    Groupon queryGrouponByOrderId(Integer id);
    /**
     * 此mapper及对应service 控制 如下 表数据
     * order
     * aftersale
     * groupop
     * groupon_rules
     */

}
