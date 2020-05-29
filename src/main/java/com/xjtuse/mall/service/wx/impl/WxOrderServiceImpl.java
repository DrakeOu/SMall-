package com.xjtuse.mall.service.wx.impl;

import com.xjtuse.mall.bean.mall.Order;
import com.xjtuse.mall.bean.promotion.Groupon;
import com.xjtuse.mall.mapper.wx.WxOrderMapper;
import com.xjtuse.mall.service.wx.WxOrderService;
import com.xjtuse.mall.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    WxOrderMapper orderMapper;

    @Override
    public List<Groupon> queryTwoGroupon() {
        return orderMapper.queryTwoGroupon();
    }

    @Override
    public Object orderInfo(Integer userId) {
        List<Order> orderList = orderMapper.queryOrderList(userId);

        Integer unpaid = 0;
        Integer unship = 0;
        Integer unrecv = 0;
        Integer uncomment = 0;

        for(Order order: orderList){
            if(OrderUtil.isCreateStatus(order)){
                unpaid++;
            }else if(OrderUtil.hasPayed(order)){
                unship++;
            }else if(OrderUtil.isShipStatus(order)){
                unrecv++;
            }else if(OrderUtil.isConfirmStatus(order)||OrderUtil.isAutoConfirmStatus(order)){
                uncomment++;
            }
        }
        Map<Object, Object> orderInfo = new HashMap();
        orderInfo.put("unpaid", unpaid);
        orderInfo.put("unship", unship);
        orderInfo.put("unrecv", unrecv);
        orderInfo.put("uncomment", uncomment);
        return orderInfo;
    }
}
