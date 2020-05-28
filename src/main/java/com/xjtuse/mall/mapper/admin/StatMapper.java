package com.xjtuse.mall.mapper.admin;

import com.xjtuse.mall.bean.mall.Order;
import com.xjtuse.mall.bean.mall.OrderAndGoods;
import com.xjtuse.mall.bean.user.User;

import java.util.Date;
import java.util.List;

public interface StatMapper {

    List<User> queryAllUser();

    List<Date> queryAllUserAddDate();

    int queryNewUserOfDate(String date);

    List<Order> queryOrderByUser(User user);

    List<Date> queryAllOrderDate();

    List<Order> queryOrderByDate(String date);

    OrderAndGoods queryGoodsCount(Order order);
}
