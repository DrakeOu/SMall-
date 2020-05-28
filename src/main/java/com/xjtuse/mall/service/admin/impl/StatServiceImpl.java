package com.xjtuse.mall.service.admin.impl;

import com.xjtuse.mall.bean.mall.Order;
import com.xjtuse.mall.bean.mall.OrderAndGoods;
import com.xjtuse.mall.bean.stat.VChart;
import com.xjtuse.mall.common.Constants;
import com.xjtuse.mall.mapper.admin.StatMapper;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.admin.StatService;
import com.xjtuse.mall.utils.ResultUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class StatServiceImpl implements StatService, ApplicationContextAware {

    @Autowired
    StatMapper mapper;

    private ApplicationContext applicationContext;

    @Override
    public TResultVo queryUserStat() {
        VChart vChart = (VChart) applicationContext.getBean("vChart");
        vChart.setColumns(Constants.USER_STAT_COLUMNS);
        //查询出有用户注册的日期
        List<Date> dates = mapper.queryAllUserAddDate();
        for (Date date :
                dates) {
            //查询当天注册人数
            int newUserCount = mapper.queryNewUserOfDate(formatDate(date));
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("users", newUserCount);
            itemMap.put("date", formatDate(date));
            vChart.addRowsItem(itemMap);
        }
        return ResultUtil.genSuccessResult(vChart);
    }

    @Override
    public TResultVo queryOrderStat() {
        VChart vChart = (VChart) applicationContext.getBean("vChart");
        vChart.setColumns(Constants.ORDER_STAT_COLUMNS);
        List<Date> dates = mapper.queryAllOrderDate();
        for (Date date :
                dates) {
            List<Order> orders = mapper.queryOrderByDate(formatDate(date));
            //计算当日订单总额
            BigDecimal amount = calculateAmount(orders);
            //计算订单数
            int orderCount = orders.size();
            //计算下单顾客数
            int customNum = calculateCustomNum(orders);
            //计算客单价
            BigDecimal pcr = BigDecimal.valueOf(0);
            if(orderCount != 0){
                pcr = amount.divide(BigDecimal.valueOf(customNum));
            }
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("date", formatDate(date));
            itemMap.put("orders", orderCount);
            itemMap.put("customers", customNum);
            itemMap.put("amount", amount);
            itemMap.put("pcr", pcr);
            vChart.addRowsItem(itemMap);
        }
        return ResultUtil.genSuccessResult(vChart);
    }

    @Override
    public TResultVo queryGoodsStat() {
        VChart vChart = (VChart) applicationContext.getBean("vChart");
        vChart.setColumns(Constants.GOODS_STAT_COLUMNS);
        //查询到有订单发生的日期
        List<Date> dates = mapper.queryAllOrderDate();
        //按日期生成当天VO
        for (Date date :
                dates) {
            //查询当天的全部订单
            List<Order> orders = mapper.queryOrderByDate(formatDate(date));
            //计算当天订单总额
            BigDecimal amount = calculateAmount(orders);
            //计算当天货品总额
            int count = calculateGoodsCount(orders);
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("date", formatDate(date));
            itemMap.put("orders", orders.size());
            itemMap.put("products", count);
            itemMap.put("amount", amount);
            vChart.addRowsItem(itemMap);
        }
        return ResultUtil.genSuccessResult(vChart);
    }

    private BigDecimal calculateAmount(List<Order> orderList){
        BigDecimal amount = BigDecimal.valueOf(0);
        for (Order order :
                orderList) {
            amount.add(order.getOrderPrice());
        }
        return amount;
    }

    private String formatDate(Date date){
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return java.sql.Date.valueOf(localDate).toString();
    }

    private int calculateGoodsCount(List<Order> orders){
        int result = 0;
        for (Order order :
                orders) {
            OrderAndGoods orderAndGoods = mapper.queryGoodsCount(order);
            if(orderAndGoods != null && orderAndGoods.getNumber() != null){
                result += orderAndGoods.getNumber();
            }
        }
        return result;
    }

    private int calculateCustomNum(List<Order> orders){
        Set<Integer> set = new HashSet<>();
        for (Order order :
                orders) {
            set.add(order.getUserId());
        }
        return set.size();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
