package com.xjtuse.mall.controller.wx;


import com.xjtuse.mall.bean.mall.Order;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxOrderService;
import com.xjtuse.mall.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class WxOrderController {

    @Autowired
    WxOrderService orderService;

    @RequestMapping("/order/list")
    public TResultVo orderList(PageUtil pageUtil, Order order){
        return null;
    }

}
