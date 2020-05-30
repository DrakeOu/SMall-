package com.xjtuse.mall.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SystemConfig {
    public static final String LITEMALL_WX_INDEX_NEW = "litemall_wx_index_new";
    public static final String LITEMALL_WX_INDEX_HOT = "litemall_wx_index_hot";
    public static final String LITEMALL_WX_INDEX_BRAND = "litemall_wx_index_brand";
    public static final String LITEMALL_WX_INDEX_TOPIC = "litemall_wx_index_topic";
    public static final String LITEMALL_WX_INDEX_CATLOG_LIST = "litemall_wx_catlog_list";
    public static final String LITEMALL_WX_INDEX_CATLOG_GOODS = "litemall_wx_catlog_goods";
    public static final String LITEMALL_WX_SHARE = "litemall_wx_share";
    public static final String LITEMALL_EXPRESS_FREIGHT_VALUE = "litemall_express_freight_value";
    public static final String LITEMALL_EXPRESS_FREIGHT_MIN = "litemall_express_freight_min";
    public static final String LITEMALL_ORDER_UNPAID = "litemall_order_unpaid";
    public static final String LITEMALL_ORDER_UNCONFIRM = "litemall_order_unconfirm";
    public static final String LITEMALL_ORDER_COMMENT = "litemall_order_comment";
    public static final String LITEMALL_MALL_NAME = "litemall_mall_name";
    public static final String LITEMALL_MALL_ADDRESS = "litemall_mall_address";
    public static final String LITEMALL_MALL_PHONE = "litemall_mall_phone";
    public static final String LITEMALL_MALL_QQ = "litemall_mall_qq";
    public static final String LITEMALL_MALL_LONGITUDE = "litemall_mall_longitude";
    public static final String LITEMALL_MALL_Latitude = "litemall_mall_latitude";
    private static Map<String, String> SYSTEM_CONFIGS = new HashMap();

    public SystemConfig() {
    }

    private static String getConfig(String keyName) {
        return (String)SYSTEM_CONFIGS.get(keyName);
    }

    private static Integer getConfigInt(String keyName) {
        return Integer.parseInt((String)SYSTEM_CONFIGS.get(keyName));
    }

    private static Boolean getConfigBoolean(String keyName) {
        return Boolean.valueOf((String)SYSTEM_CONFIGS.get(keyName));
    }

    private static BigDecimal getConfigBigDec(String keyName) {
        return new BigDecimal((String)SYSTEM_CONFIGS.get(keyName));
    }

    public static Integer getNewLimit() {
        return getConfigInt("litemall_wx_index_new");
    }

    public static Integer getHotLimit() {
        return getConfigInt("litemall_wx_index_hot");
    }

    public static Integer getBrandLimit() {
        return getConfigInt("litemall_wx_index_brand");
    }

    public static Integer getTopicLimit() {
        return getConfigInt("litemall_wx_index_topic");
    }

    public static Integer getCatlogListLimit() {
        return getConfigInt("litemall_wx_catlog_list");
    }

    public static Integer getCatlogMoreLimit() {
        return getConfigInt("litemall_wx_catlog_goods");
    }

    public static boolean isAutoCreateShareImage() {
        return getConfigBoolean("litemall_wx_share");
    }

    public static BigDecimal getFreight() {
        return getConfigBigDec("litemall_express_freight_value");
    }

    public static BigDecimal getFreightLimit() {
        return getConfigBigDec("litemall_express_freight_min");
    }

    public static Integer getOrderUnpaid() {
        return getConfigInt("litemall_order_unpaid");
    }

    public static Integer getOrderUnconfirm() {
        return getConfigInt("litemall_order_unconfirm");
    }

    public static Integer getOrderComment() {
        return getConfigInt("litemall_order_comment");
    }

    public static String getMallName() {
        return getConfig("litemall_mall_name");
    }

    public static String getMallAddress() {
        return getConfig("litemall_mall_address");
    }

    public static String getMallPhone() {
        return getConfig("litemall_mall_phone");
    }

    public static String getMallQQ() {
        return getConfig("litemall_mall_qq");
    }

    public static String getMallLongitude() {
        return getConfig("litemall_mall_longitude");
    }

    public static String getMallLatitude() {
        return getConfig("litemall_mall_latitude");
    }

    public static void setConfigs(Map<String, String> configs) {
        SYSTEM_CONFIGS = configs;
    }

    public static void updateConfigs(Map<String, String> data) {
        Iterator var1 = data.entrySet().iterator();

        while(var1.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)var1.next();
            SYSTEM_CONFIGS.put(entry.getKey(), entry.getValue());
        }

    }
}
