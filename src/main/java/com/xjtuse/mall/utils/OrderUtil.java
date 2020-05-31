package com.xjtuse.mall.utils;

import com.xjtuse.mall.bean.mall.Order;
import com.xjtuse.mall.common.OrderHandleOption;

import java.util.ArrayList;
import java.util.List;

public class OrderUtil {
    public static final Short STATUS_CREATE = Short.valueOf((short)101);
    public static final Short STATUS_PAY = 201;
    public static final Short STATUS_SHIP = 301;
    public static final Short STATUS_CONFIRM = 401;
    public static final Short STATUS_CANCEL = Short.valueOf((short)102);
    public static final Short STATUS_AUTO_CANCEL = Short.valueOf((short)103);
    public static final Short STATUS_ADMIN_CANCEL = Short.valueOf((short)104);
    public static final Short STATUS_REFUND = 202;
    public static final Short STATUS_REFUND_CONFIRM = 203;
    public static final Short STATUS_AUTO_CONFIRM = 402;

    public static String orderStatusText(Order order) {
        int status = order.getOrderStatus().intValue();
        if (status == 101) {
            return "未付款";
        } else if (status == 102) {
            return "已取消";
        } else if (status == 103) {
            return "已取消(系统)";
        } else if (status == 201) {
            return "已付款";
        } else if (status == 202) {
            return "订单取消，退款中";
        } else if (status == 203) {
            return "已退款";
        } else if (status == 204) {
            return "已超时团购";
        } else if (status == 301) {
            return "已发货";
        } else if (status == 401) {
            return "已收货";
        } else if (status == 402) {
            return "已收货(系统)";
        } else {
            throw new IllegalStateException("orderStatus不支持");
        }
    }



    public static OrderHandleOption build(Order order) {
        int status = order.getOrderStatus().intValue();
        OrderHandleOption handleOption = new OrderHandleOption();
        if (status == 101) {
            handleOption.setCancel(true);
            handleOption.setPay(true);
        } else if (status != 102 && status != 103) {
            if (status == 201) {
                handleOption.setRefund(true);
            } else if (status != 202 && status != 204) {
                if (status == 203) {
                    handleOption.setDelete(true);
                } else if (status == 301) {
                    handleOption.setConfirm(true);
                } else {
                    if (status != 401 && status != 402) {
                        throw new IllegalStateException("status不支持");
                    }

                    handleOption.setDelete(true);
                    handleOption.setComment(true);
                    handleOption.setRebuy(true);
                    handleOption.setAftersale(true);
                }
            }
        } else {
            handleOption.setDelete(true);
        }

        return handleOption;
    }
    public static List<Short> orderStatus(Integer showType) {
        if (showType == 0) {
            return null;
        } else {
            List<Short> status = new ArrayList(2);
            if (showType.equals(1)) {
                status.add(Short.valueOf((short)101));
            } else if (showType.equals(2)) {
                status.add((short)201);
            } else if (showType.equals(3)) {
                status.add((short)301);
            } else {
                if (!showType.equals(4)) {
                    return null;
                }

                status.add((short)401);
            }

            return status;
        }
    }

    public static boolean isCreateStatus(Order order) {
        return STATUS_CREATE == order.getOrderStatus().shortValue();
    }

    public static boolean hasPayed(Order order) {
        return STATUS_CREATE != order.getOrderStatus().shortValue() && STATUS_CANCEL != order.getOrderStatus().shortValue() && STATUS_AUTO_CANCEL != order.getOrderStatus().shortValue();
    }

    public static boolean isPayStatus(Order order) {
        return STATUS_PAY == order.getOrderStatus().shortValue();
    }

    public static boolean isShipStatus(Order order) {
        return STATUS_SHIP == order.getOrderStatus().shortValue();
    }

    public static boolean isConfirmStatus(Order order) {
        return STATUS_CONFIRM == order.getOrderStatus().shortValue();
    }

    public static boolean isCancelStatus(Order order) {
        return STATUS_CANCEL == order.getOrderStatus().shortValue();
    }

    public static boolean isAutoCancelStatus(Order order) {
        return STATUS_AUTO_CANCEL == order.getOrderStatus().shortValue();
    }

    public static boolean isRefundStatus(Order order) {
        return STATUS_REFUND == order.getOrderStatus().shortValue();
    }

    public static boolean isRefundConfirmStatus(Order order) {
        return STATUS_REFUND_CONFIRM == order.getOrderStatus().shortValue();
    }

    public static boolean isAutoConfirmStatus(Order order) {
        return STATUS_AUTO_CONFIRM == order.getOrderStatus().shortValue();
    }



}
