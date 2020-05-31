package com.xjtuse.mall.controller.wx;


import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;
import com.xjtuse.mall.annotation.LoginUser;
import com.xjtuse.mall.bean.mall.Order;
import com.xjtuse.mall.bean.mall.OrderAndGoods;
import com.xjtuse.mall.bean.promotion.Groupon;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxGoodsService;
import com.xjtuse.mall.service.wx.WxOrderService;
import com.xjtuse.mall.utils.OrderUtil;
import com.xjtuse.mall.utils.PageUtil;
import com.xjtuse.mall.utils.ResultUtil;
import com.xjtuse.mall.utils.VoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class WxOrderController {

    @Autowired
    WxOrderService orderService;

    @Autowired
    WxGoodsService goodsService;

    @RequestMapping("/order/list")
    public TResultVo orderList(PageUtil pageUtil, Integer showType, @LoginUser Integer userId){
        if (userId == null) {
            return ResultUtil.unlogin();
        }

        List<Short> orderStatus = OrderUtil.orderStatus(showType);
        List<Order> orderList = orderService.queryByOrderStatus(userId, orderStatus, pageUtil);

        List<Map<String, Object>> orderVoList = new ArrayList<>(orderList.size());
        for (Order o : orderList) {
            Map<String, Object> orderVo = new HashMap<>();
            orderVo.put("id", o.getId());
            orderVo.put("orderSn", o.getOrderSn());
            orderVo.put("actualPrice", o.getActualPrice());
            orderVo.put("orderStatusText", OrderUtil.orderStatusText(o));
            orderVo.put("handleOption", OrderUtil.build(o));
            orderVo.put("aftersaleStatus", o.getAftersaleStatus());

            Groupon groupon = orderService.queryGrouponByOrderId(o.getId());
            if (groupon != null) {
                orderVo.put("isGroupin", true);
            } else {
                orderVo.put("isGroupin", false);
            }

            List<OrderAndGoods> orderGoodsList = goodsService.queryOrderGoodsList(o.getId());
            List<Map<String, Object>> orderGoodsVoList = new ArrayList<>(orderGoodsList.size());
            for (OrderAndGoods orderGoods : orderGoodsList) {
                Map<String, Object> orderGoodsVo = new HashMap<>();
                orderGoodsVo.put("id", orderGoods.getId());
                orderGoodsVo.put("goodsName", orderGoods.getGoodsName());
                orderGoodsVo.put("number", orderGoods.getNumber());
                orderGoodsVo.put("picUrl", orderGoods.getPicUrl());
                orderGoodsVo.put("specifications", orderGoods.getSpecifications());
                orderGoodsVo.put("price",orderGoods.getPrice());
                orderGoodsVoList.add(orderGoodsVo);
            }
            orderVo.put("goodsList", orderGoodsVoList);

            orderVoList.add(orderVo);
        }

        return ResultUtil.genSuccessResult(VoUtil.genListVo(
                orderList.size(),
                1 + orderList.size()/pageUtil.getLimit(),
                pageUtil.getLimit(),
                pageUtil.getPage(),
                orderVoList
        ));
    }


    /**
     * 订单详情
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @return 订单详情
     */
    @GetMapping("/order/detail")
    public TResultVo detail(@LoginUser Integer userId, @NotNull Integer orderId) {
        return orderService.queryOrderDetail(userId, orderId);
    }

    /**
     * 提交订单
     *
     * @param userId 用户ID
     * @param body   订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 提交订单操作结果
     */
    @PostMapping("/order/submit")
    public TResultVo submit(@LoginUser Integer userId, @RequestBody String body) {
        return orderService.orderSubmit(userId, body);
    }

    /**
     * 取消订单
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 取消订单操作结果
     */
    @PostMapping("/order/cancel")
    public TResultVo cancel(@LoginUser Integer userId, @RequestBody String body) {
        return orderService.orderCancel(userId, body);
    }

    /**
     * 付款订单的预支付会话标识
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 支付订单ID
     */
    @PostMapping("/order/prepay")
    public TResultVo prepay(@LoginUser Integer userId, @RequestBody String body, HttpServletRequest request) {
        return orderService.orderPrepay(userId, body, request);
    }

    /**
     * 微信H5支付
     * @param userId
     * @param body
     * @param request
     * @return
     */
    @PostMapping("/order/h5pay")
    public TResultVo h5pay(@LoginUser Integer userId, @RequestBody String body, HttpServletRequest request) {
        return orderService.preH5pay(userId, body, request);
    }

    /**
     * 微信付款成功或失败回调接口
     * <p>
     *  TODO
     *  注意，这里pay-notify是示例地址，建议开发者应该设立一个隐蔽的回调地址
     *
     * @param request 请求内容
     * @param response 响应内容
     * @return 操作结果
     */
    @PostMapping("/order/pay-notify")
    public TResultVo payNotify(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     * 订单申请退款
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    @PostMapping("/order/refund")
    public TResultVo refund(@LoginUser Integer userId, @RequestBody String body) {
        return orderService.orderRefund(userId, body);
    }

    /**
     * 确认收货
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("/order/confirm")
    public TResultVo confirm(@LoginUser Integer userId, @RequestBody String body) {
        return orderService.orderConfirm(userId, body);
    }

    /**
     * 删除订单
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("/order/delete")
    public TResultVo delete(@LoginUser Integer userId, @RequestBody String body) {
        return orderService.orderDelete(userId, body);
    }

    /**
     * 待评价订单商品信息
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @param goodsId 商品ID
     * @return 待评价订单商品信息
     */
    @GetMapping("/order/goods")
    public TResultVo goods(@LoginUser Integer userId,
                        @NotNull Integer orderId,
                        @NotNull Integer goodsId) {
        return orderService.orderGoods(userId, orderId, goodsId);
    }

    /**
     * 评价订单商品
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("/order/comment")
    public TResultVo comment(@LoginUser Integer userId, @RequestBody String body) {
        return orderService.orderComment(userId, body);
    }
}
