package com.xjtuse.mall.utils;

import com.xjtuse.mall.bean.mall.Order;

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
