package com.xjtuse.mall.service.wx;

import com.xjtuse.mall.bean.mall.Order;
import com.xjtuse.mall.bean.mall.OrderAndGoods;
import com.xjtuse.mall.bean.promotion.Groupon;
import com.xjtuse.mall.bean.promotion.GrouponRules;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.utils.PageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface WxOrderService {
    List<Groupon> queryTwoGroupon();

    Object orderInfo(Integer userId);

    GrouponRules queryGrouponRulesById(Integer grouponRulesId);

    TResultVo queryOrderDetail(Integer userId, Integer orderId);

    TResultVo orderSubmit(Integer userId, String body);

    void addOrder(Order order);

    String generateOrderSn(Integer userId);

    Groupon queryGrouponById(Integer userId, Integer grouponLinkId);

    boolean hasJoinGroupon(Integer userId, Integer grouponLinkId);

    int countGroupon(Integer grouponLinkId);

    TResultVo orderCancel(Integer userId, String body);

    TResultVo orderPrepay(Integer userId, String body, HttpServletRequest request);

    TResultVo orderRefund(Integer userId, String body);

    TResultVo preH5pay(Integer userId, String body, HttpServletRequest request);

    TResultVo orderConfirm(Integer userId, String body);

    TResultVo orderDelete(Integer userId, String body);

    TResultVo orderGoods(Integer userId, Integer orderId, Integer goodsId);

    TResultVo orderComment(Integer userId, String body);

    void createGroupon(Groupon groupon);

    int updateWithOptimisticLocker(Order order);

    Order queryOrderById(Integer userId, Integer orderId);

    void deleteOrderById(Integer orderId);

    void deleteAftersaleByOrderId(Integer userId, Integer orderId);

    Order queryOrderByOsn(String orderSn);

    List<Order> queryByOrderStatus(Integer userId, List<Short> orderStatus, PageUtil pageUtil);

    Groupon queryGrouponByOrderId(Integer id);
}
