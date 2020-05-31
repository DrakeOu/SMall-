package com.xjtuse.mall.service.wx.impl;

import com.xjtuse.mall.bean.goods.Comment;
import com.xjtuse.mall.bean.goods.GoodsProduct;
import com.xjtuse.mall.bean.mall.Order;
import com.xjtuse.mall.bean.mall.OrderAndGoods;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.promotion.CouponAndUser;
import com.xjtuse.mall.bean.promotion.Groupon;
import com.xjtuse.mall.bean.promotion.GrouponRules;
import com.xjtuse.mall.bean.user.Cart;
import com.xjtuse.mall.bean.user.LitemallAddress;
import com.xjtuse.mall.bean.user.User;
import com.xjtuse.mall.common.*;
import com.xjtuse.mall.mapper.wx.WxOrderMapper;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxGoodsService;
import com.xjtuse.mall.service.wx.WxOrderService;
import com.xjtuse.mall.service.wx.WxUserService;
import com.xjtuse.mall.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    WxOrderMapper orderMapper;

    @Autowired
    ExpressService expressService;

    @Autowired
    WxUserService userService;

    @Autowired
    WxGoodsService goodsService;
    
    @Autowired
    WxOrderService orderService;

    @Autowired
    TaskService taskService;

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

    @Override
    public GrouponRules queryGrouponRulesById(Integer grouponRulesId) {
        return orderMapper.queryGrouponRulesById(grouponRulesId);
    }

    @Override
    public TResultVo queryOrderDetail(Integer userId, Integer orderId) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }

        // 订单信息
        Order order = queryOrderById(userId, orderId);
        if (null == order) {
            return ResultUtil.genFailResult("订单不存在");
        }
        if (!order.getUserId().equals(userId)) {
            return ResultUtil.genFailResult("不是当前用户的订单");
        }
        Map<String, Object> orderVo = new HashMap<String, Object>();
        orderVo.put("id", order.getId());
        orderVo.put("orderSn", order.getOrderSn());
        orderVo.put("message", order.getMessage());
        orderVo.put("addTime", order.getAddTime());
        orderVo.put("consignee", order.getConsignee());
        orderVo.put("mobile", order.getMobile());
        orderVo.put("address", order.getAddress());
        orderVo.put("goodsPrice", order.getGoodsPrice());
        orderVo.put("couponPrice", order.getCouponPrice());
        orderVo.put("freightPrice", order.getFreightPrice());
        orderVo.put("actualPrice", order.getActualPrice());
        orderVo.put("orderStatusText", OrderUtil.orderStatusText(order));
        orderVo.put("handleOption", OrderUtil.build(order));
        orderVo.put("aftersaleStatus", order.getAftersaleStatus());
        orderVo.put("expCode", order.getShipChannel());
        orderVo.put("expName", expressService.getVendorName(order.getShipChannel()));
        orderVo.put("expNo", order.getShipSn());

        List<OrderAndGoods> orderGoodsList = goodsService.queryOrderAndGoodsByOid(order.getId());

        Map<String, Object> result = new HashMap<>();
        result.put("orderInfo", orderVo);
        result.put("orderGoods", orderGoodsList);

        // 订单状态为已发货且物流信息不为空
        //"YTO", "800669400640887922"
        if (order.getOrderStatus().equals(OrderUtil.STATUS_SHIP)) {
            ExpressInfo ei = expressService.getExpressInfo(order.getShipChannel(), order.getShipSn());
            if(ei == null){
                result.put("expressInfo", new ArrayList<>());
            }
            else {
                result.put("expressInfo", ei);
            }
        }
        else{
            result.put("expressInfo", new ArrayList<>());
        }

        return ResultUtil.genSuccessResult(result);
    }

    public Order queryOrderById(Integer userId, Integer orderId) {
        return orderMapper.queryOrderById(userId, orderId);
    }

    @Override
    public void deleteOrderById(Integer orderId) {
        orderMapper.deleteOrderById(orderId);
    }

    @Override
    public void deleteAftersaleByOrderId(Integer userId, Integer orderId) {
        orderMapper.deleteAftersaleByOrderId(userId, orderId);
    }

    @Override
    public Order queryOrderByOsn(String orderSn) {
        return orderMapper.queryOrderByOsn(orderSn);
    }

    @Override
    public List<Order> queryByOrderStatus(Integer userId, List<Short> orderStatus, PageUtil pageUtil) {
        if(orderStatus!=null && orderStatus.size()==1){
            Short status = orderStatus.get(0);
            return orderMapper.queryByOrderStatus(userId, status, pageUtil);
        }
        return orderMapper.queryOrderList(userId);
    }

    @Override
    public Groupon queryGrouponByOrderId(Integer id) {
        return orderMapper.queryGrouponByOrderId(id);
    }

    @Override
    public TResultVo orderSubmit(Integer userId, String body) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }
        if (body == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        Integer cartId = JsonUtil.parseInteger(body, "cartId");
        Integer addressId = JsonUtil.parseInteger(body, "addressId");
        Integer couponId = JsonUtil.parseInteger(body, "couponId");
        Integer userCouponId = JsonUtil.parseInteger(body, "userCouponId");
        String message = JsonUtil.parseString(body, "message");
        Integer grouponRulesId = JsonUtil.parseInteger(body, "grouponRulesId");
        Integer grouponLinkId = JsonUtil.parseInteger(body, "grouponLinkId");

        //如果是团购项目,验证活动是否有效
        if (grouponRulesId != null && grouponRulesId > 0) {
            GrouponRules rules = queryGrouponRulesById(grouponRulesId);
            //找不到记录
            if (rules == null) {
                return ResultUtil.genFailResult("参数错误");
            }
            //团购规则已经过期
            if (rules.getStatus().equals(GrouponConstant.RULE_STATUS_DOWN_EXPIRE)) {
                return ResultUtil.genFailResult("团购已过期");
            }
            //团购规则已经下线
            if (rules.getStatus().equals(GrouponConstant.RULE_STATUS_DOWN_ADMIN)) {
                return ResultUtil.genFailResult("团购已下线");
            }

            if (grouponLinkId != null && grouponLinkId > 0) {
                //团购人数已满
                if(countGroupon(grouponLinkId) >= (rules.getDiscountMember() - 1)){
                    return ResultUtil.genFailResult("团购活动人数已满");
                }
                // NOTE
                // 这里业务方面允许用户多次开团，以及多次参团，
                // 但是会限制以下两点：
                // （1）不允许参加已经加入的团购
                if(hasJoinGroupon(userId, grouponLinkId)){
                    return ResultUtil.genFailResult("团购活动已经参加");
                }
                // （2）不允许参加自己开团的团购
                Groupon groupon = queryGrouponById(userId, grouponLinkId);
                if(groupon.getCreatorUserId().equals(userId)){
                    return ResultUtil.genFailResult("团购活动已经参加");
                }
            }
        }

        if (cartId == null || addressId == null || couponId == null) {
            return ResultUtil.genFailResult("参数错误");
        }

        // 收货地址
        LitemallAddress checkedAddress = userService.queryAddress(userId, addressId);
        if (checkedAddress == null) {
            return ResultUtil.genFailResult("参数错误");
        }

        // 团购优惠
        BigDecimal grouponPrice = new BigDecimal(0);
        GrouponRules grouponRules = queryGrouponRulesById(grouponRulesId);
        if (grouponRules != null) {
            grouponPrice = grouponRules.getDiscount();
        }

        // 货品价格
        List<Cart> checkedGoodsList = null;
        if (cartId.equals(0)) {
            checkedGoodsList = userService.queryCartByUidAndChecked(userId);
        } else {
            Cart cart = userService.queryCartByCid(null, cartId);
            checkedGoodsList = new ArrayList<>(1);
            checkedGoodsList.add(cart);
        }
        if (checkedGoodsList.size() == 0) {
            return ResultUtil.genFailResult("参数错误");
        }
        BigDecimal checkedGoodsPrice = new BigDecimal(0);
        for (Cart checkGoods : checkedGoodsList) {
            //  只有当团购规格商品ID符合才进行团购优惠
            if (grouponRules != null && grouponRules.getGoodsId().equals(checkGoods.getGoodsId())) {
                checkedGoodsPrice = checkedGoodsPrice.add(checkGoods.getPrice().subtract(grouponPrice).multiply(new BigDecimal(checkGoods.getNumber())));
            } else {
                checkedGoodsPrice = checkedGoodsPrice.add(checkGoods.getPrice().multiply(new BigDecimal(checkGoods.getNumber())));
            }
        }

        // 获取可用的优惠券信息
        // 使用优惠券减免的金额
        BigDecimal couponPrice = new BigDecimal(0);
        // 如果couponId=0则没有优惠券，couponId=-1则不使用优惠券
        if (couponId != 0 && couponId != -1) {
            Coupon coupon = userService.checkCoupon(userId, couponId, userCouponId, checkedGoodsPrice);
            if (coupon == null) {
                return ResultUtil.genFailResult("参数错误");
            }
            couponPrice = coupon.getDiscount();
        }


        // 根据订单商品总价计算运费，满足条件（例如88元）则免运费，否则需要支付运费（例如8元）；
        BigDecimal freightPrice = new BigDecimal(0);
        if (checkedGoodsPrice.compareTo(new BigDecimal(88)) < 0) {
            freightPrice = new BigDecimal(8);
        }

        // 可以使用的其他钱，例如用户积分
        BigDecimal integralPrice = new BigDecimal(0);

        // 订单费用
        BigDecimal orderTotalPrice = checkedGoodsPrice.add(freightPrice).subtract(couponPrice).max(new BigDecimal(0));
        // 最终支付费用
        BigDecimal actualPrice = orderTotalPrice.subtract(integralPrice);

        Integer orderId = null;
        Order order = null;
        // 订单
        order = new Order();
        order.setUserId(userId);
        order.setOrderSn(generateOrderSn(userId));
        order.setOrderStatus(OrderUtil.STATUS_CREATE.intValue());
        order.setConsignee(checkedAddress.getName());
        order.setMobile(checkedAddress.getTel());
        order.setMessage(message);
        String detailedAddress = checkedAddress.getProvince() + checkedAddress.getCity() + checkedAddress.getCounty() + " " + checkedAddress.getAddressDetail();
        order.setAddress(detailedAddress);
        order.setGoodsPrice(checkedGoodsPrice);
        order.setFreightPrice(freightPrice);
        order.setCouponPrice(couponPrice);
        order.setIntegralPrice(integralPrice);
        order.setOrderPrice(orderTotalPrice);
        order.setActualPrice(actualPrice);

        // 有团购
        if (grouponRules != null) {
            order.setGrouponPrice(grouponPrice);    //  团购价格
        } else {
            order.setGrouponPrice(new BigDecimal(0));    //  团购价格
        }

        // 添加订单表项
        addOrder(order);
        Order order1 = orderService.queryOrderByOsn(order.getOrderSn());
        orderId = order1.getId();

        // 添加订单商品表项
        for (Cart cartGoods : checkedGoodsList) {
            // 订单商品
            OrderAndGoods orderGoods = new OrderAndGoods();
            orderGoods.setOrderId(orderId);
            orderGoods.setGoodsId(cartGoods.getGoodsId());
            orderGoods.setGoodsSn(cartGoods.getGoodsSn());
            orderGoods.setProductId(cartGoods.getProductId());
            orderGoods.setGoodsName(cartGoods.getGoodsName());
            orderGoods.setPicUrl(cartGoods.getPicUrl());
            orderGoods.setPrice(cartGoods.getPrice());
            orderGoods.setNumber(cartGoods.getNumber().intValue());
            orderGoods.setSpecifications(cartGoods.getSpecifications().toString());
            orderGoods.setAddTime(DateUtil.asDate(LocalDateTime.now()));

            goodsService.addOrderAndGoods(orderGoods);
        }

        // 删除购物车里面的商品信息
        userService.clearCart(userId);

        // 商品货品数量减少
        for (Cart checkGoods : checkedGoodsList) {
            Integer productId = checkGoods.getProductId();
            GoodsProduct product = goodsService.queryProductByPid(productId);

            int remainNumber = product.getNumber() - checkGoods.getNumber();
            if (remainNumber < 0) {
                throw new RuntimeException("下单的商品货品数量大于库存量");
            }
            if (goodsService.reduceStock(productId, checkGoods.getNumber()) == 0) {
                throw new RuntimeException("商品货品库存减少失败");
            }
        }

        // 如果使用了优惠券，设置优惠券使用状态
        if (couponId != 0 && couponId != -1) {
            CouponAndUser couponUser = userService.queryCouponAndUserById(userCouponId);
            couponUser.setStatus(CouponUserConstant.STATUS_USED.intValue());
            couponUser.setUsedTime(LocalDateTime.now());
            couponUser.setOrderId(orderId);
            userService.updateCouponUser(couponUser);
        }

        //如果是团购项目，添加团购信息
        if (grouponRulesId != null && grouponRulesId > 0) {
            Groupon groupon = new Groupon();
            groupon.setOrderId(orderId);
            groupon.setStatus(GrouponConstant.STATUS_NONE);
            groupon.setUserId(userId);
            groupon.setRulesId(grouponRulesId);

            //参与者
            if (grouponLinkId != null && grouponLinkId > 0) {
                //参与的团购记录
                Groupon baseGroupon = queryGrouponById(userId, grouponLinkId);
                groupon.setCreatorUserId(baseGroupon.getCreatorUserId());
                groupon.setGrouponId(grouponLinkId);
                groupon.setShareUrl(baseGroupon.getShareUrl());
                orderService.createGroupon(groupon);
            } else {
                groupon.setCreatorUserId(userId);
                groupon.setCreatorUserTime(DateUtil.asDate(LocalDateTime.now()));
                groupon.setGrouponId(0);
                orderService.createGroupon(groupon);
                grouponLinkId = groupon.getId();
            }
        }

        // 订单支付超期任务
        taskService.addTask(new OrderUnpaidTask(orderId));

        Map<String, Object> data = new HashMap<>();
        data.put("orderId", orderId);
        if (grouponRulesId != null && grouponRulesId > 0) {
            data.put("grouponLinkId", grouponLinkId);
        }
        else {
            data.put("grouponLinkId", 0);
        }
        return ResultUtil.genSuccessResult(data);
    }

    @Override
    public void addOrder(Order order) {
        orderMapper.addOrder(order);
    }

    @Override
    public String generateOrderSn(Integer userId) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = df.format(LocalDate.now());

        String orderSn = now + UUIDUtils.getUUID().substring(0, 25);
        return orderSn;
    }

    @Override
    public Groupon queryGrouponById(Integer userId, Integer grouponLinkId) {
        return orderMapper.queryGrouponById(userId, grouponLinkId);
    }

    @Override
    public boolean hasJoinGroupon(Integer userId, Integer grouponLinkId) {

        return orderMapper.hasJoinGroupon(userId, grouponLinkId);
    }

    @Override
    public int countGroupon(Integer grouponLinkId) {
        return orderMapper.countGroupon(grouponLinkId);
    }

    @Override
    public TResultVo orderCancel(Integer userId, String body) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }
        Integer orderId = JsonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResultUtil.genFailResult("参数错误");
        }

        Order order = orderService.queryOrderById(userId, orderId);
        if (order == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        if (!order.getUserId().equals(userId)) {
            return ResultUtil.genFailResult("参数错误");
        }

        LocalDateTime preUpdateTime = DateUtil.asLocalDateTime(order.getUpdateTime());

        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isCancel()) {
            return ResultUtil.genFailResult("订单不能取消");
        }

        // 设置订单已取消状态
        order.setOrderStatus(OrderUtil.STATUS_CANCEL.intValue());
        order.setEndTime(DateUtil.asDate(LocalDateTime.now()));
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            throw new RuntimeException("更新数据已失效");
        }

        // 商品货品数量增加
        List<OrderAndGoods> orderGoodsList = goodsService.queryOrderAndGoodsByOid(orderId);
        for (OrderAndGoods orderGoods : orderGoodsList) {
            Integer productId = orderGoods.getProductId();
            Short number = orderGoods.getNumber().shortValue();
            if (goodsService.addStock(productId, number) == 0) {
                throw new RuntimeException("商品货品库存增加失败");
            }
        }

        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo orderPrepay(Integer userId, String body, HttpServletRequest request) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }
        Integer orderId = JsonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResultUtil.genFailResult("参数错误");
        }

        Order order = orderService.queryOrderById(userId, orderId);
        if (order == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        if (!order.getUserId().equals(userId)) {
            return ResultUtil.genFailResult("参数错误");
        }

        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isPay()) {
            return ResultUtil.genFailResult("订单不能支付");
        }

        User user = userService.queryUserById(userId);
        String openid = user.getWeixinOpenid();
        if (openid == null) {
            return ResultUtil.genFailResult("订单不能支付");
        }
//        WxPayMpOrderResult result = null;
//        try {
//            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
//            orderRequest.setOutTradeNo(order.getOrderSn());
//            orderRequest.setOpenid(openid);
//            orderRequest.setBody("订单：" + order.getOrderSn());
//            // 元转成分
//            int fee = 0;
//            BigDecimal actualPrice = order.getActualPrice();
//            fee = actualPrice.multiply(new BigDecimal(100)).intValue();
//            orderRequest.setTotalFee(fee);
//            orderRequest.setSpbillCreateIp(IpUtil.getIpAddr(request));
//
//            result = wxPayService.createOrder(orderRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultUtil.genFailResult("订单不能支付");
//        }
//
//        if (orderService.updateWithOptimisticLocker(order) == 0) {
//            return ResultUtil.updatedDateExpired();
//        }
//        return ResultUtil.genSuccessResult(result);
        return null;
    }

    @Override
    public TResultVo orderRefund(Integer userId, String body) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }
        Integer orderId = JsonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResultUtil.genFailResult("参数错误");
        }

        Order order = orderService.queryOrderById(userId, orderId);
        if (order == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        if (!order.getUserId().equals(userId)) {
            return ResultUtil.genFailResult("参数错误");
        }

        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isRefund()) {
            return ResultUtil.genFailResult("订单不能取消");
        }

        // 设置订单申请退款状态
//        order.setOrderStatus(OrderUtil.STATUS_REFUND);
//        if (orderService.updateWithOptimisticLocker(order) == 0) {
//            return ResultUtil.updatedDateExpired();
//        }
//
//        //TODO 发送邮件和短信通知，这里采用异步发送
//        // 有用户申请退款，邮件通知运营人员
//        notifyService.notifyMail("退款申请", order.toString());

        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo preH5pay(Integer userId, String body, HttpServletRequest request) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }
        Integer orderId = JsonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResultUtil.genFailResult("参数错误");
        }

        Order order = orderService.queryOrderById(userId, orderId);
        if (order == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        if (!order.getUserId().equals(userId)) {
            return ResultUtil.genFailResult("参数错误");
        }

        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isPay()) {
            return ResultUtil.genFailResult("订单不能支付");
        }

//        WxPayMwebOrderResult result = null;
//        try {
//            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
//            orderRequest.setOutTradeNo(order.getOrderSn());
//            orderRequest.setTradeType("MWEB");
//            orderRequest.setBody("订单：" + order.getOrderSn());
//            // 元转成分
//            int fee = 0;
//            BigDecimal actualPrice = order.getActualPrice();
//            fee = actualPrice.multiply(new BigDecimal(100)).intValue();
//            orderRequest.setTotalFee(fee);
//            orderRequest.setSpbillCreateIp(IpUtil.getIpAddr(request));
//
//            result = wxPayService.createOrder(orderRequest);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return ResultUtil.genSuccessResult(result);
        return null;
    }

    @Override
    public TResultVo orderConfirm(Integer userId, String body) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }
        Integer orderId = JsonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResultUtil.genFailResult("参数错误");
        }

        Order order = orderService.queryOrderById(userId, orderId);
        if (order == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        if (!order.getUserId().equals(userId)) {
            return ResultUtil.genFailResult("参数错误");
        }

        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isConfirm()) {
            return ResultUtil.genFailResult("订单不能确认收货");
        }

        Short comments = goodsService.getComments(orderId);
        order.setComments(comments.intValue());

        order.setOrderStatus(OrderUtil.STATUS_CONFIRM.intValue());
        order.setConfirmTime(DateUtil.asDate(LocalDateTime.now()));
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            return ResultUtil.genSuccessResult();
        }
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo orderDelete(Integer userId, String body) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }
        Integer orderId = JsonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResultUtil.genFailResult("参数错误");
        }

        Order order = orderService.queryOrderById(userId, orderId);
        if (order == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        if (!order.getUserId().equals(userId)) {
            return ResultUtil.genFailResult("参数错误");
        }

        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isDelete()) {
            return ResultUtil.genFailResult("订单不能删除");
        }

        // 订单order_status没有字段用于标识删除
        // 而是存在专门的delete字段表示是否删除
        orderService.deleteOrderById(orderId);
        // 售后也同时删除
        orderService.deleteAftersaleByOrderId(userId, orderId);

        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo orderGoods(Integer userId, Integer orderId, Integer goodsId) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }
        Order order = orderService.queryOrderById(userId, orderId);
        if (order == null) {
            return ResultUtil.genFailResult("参数错误");
        }

        List<OrderAndGoods> orderGoodsList = goodsService.queryOrderGoodsList(orderId);
        int size = orderGoodsList.size();

        Assert.state(size < 2, "存在多个符合条件的订单商品");

        if (size == 0) {
            return ResultUtil.genFailResult("参数错误");
        }

        OrderAndGoods orderGoods = orderGoodsList.get(0);
        return ResultUtil.genSuccessResult(orderGoods);
    }

    @Override
    public TResultVo orderComment(Integer userId, String body) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }

        Integer orderGoodsId = JsonUtil.parseInteger(body, "orderGoodsId");
        if (orderGoodsId == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        OrderAndGoods orderGoods = goodsService.queryOrderGoodsByid(orderGoodsId);
        if (orderGoods == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        Integer orderId = orderGoods.getOrderId();
        Order order = orderService.queryOrderById(userId, orderId);
        if (order == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        Short orderStatus = order.getOrderStatus().shortValue();
        if (!OrderUtil.isConfirmStatus(order) && !OrderUtil.isAutoConfirmStatus(order)) {
            return ResultUtil.genFailResult("当前商品不能评价");
        }
        if (!order.getUserId().equals(userId)) {
            return ResultUtil.genFailResult("当前商品不属于用户");

        }
        Integer commentId = orderGoods.getComment();
        if (commentId == -1) {
            return ResultUtil.genFailResult("当前商品评价时间已经过期");
        }
        if (commentId != 0) {
            return ResultUtil.genFailResult("订单商品已评价");
        }

        String content = JsonUtil.parseString(body, "content");
        Integer star = JsonUtil.parseInteger(body, "star");
        if (star == null || star < 0 || star > 5) {
            return ResultUtil.genFailResult("参数错误");
        }
        Boolean hasPicture = JsonUtil.parseBoolean(body, "hasPicture");
        List<String> picUrls = JsonUtil.parseStringList(body, "picUrls");
        if (hasPicture == null || !hasPicture) {
            picUrls = new ArrayList<>(0);
        }

        // 1. 创建评价
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setType((byte) 0);
        comment.setValueId(orderGoods.getGoodsId());
        comment.setStar(star.shortValue());
        comment.setContent(content);
        comment.setHasPicture(hasPicture);
        comment.setPicUrls(picUrls.toArray(new String[]{}));
        userService.saveComment(comment);

        // 2. 更新订单商品的评价列表
        orderGoods.setComment(comment.getId());
//        goodsService.updateOrderGoodsById(orderGoods);

        // 3. 更新订单中未评价的订单商品可评价数量
        Short commentCount = order.getComments().shortValue();
        if (commentCount > 0) {
            commentCount--;
        }
        order.setComments(commentCount.intValue());
        orderService.updateWithOptimisticLocker(order);

        return ResultUtil.genSuccessResult();
    }

    @Override
    public void createGroupon(Groupon groupon) {
        orderMapper.createGroupon(groupon);
    }

    @Override
    public int updateWithOptimisticLocker(Order order) {
        return orderMapper.updateOrder(order);
    }


}
