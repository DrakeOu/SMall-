package com.xjtuse.mall.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
    public final static String RELATIVE_PATH = "/src/main/resources/static/img";
    public final static String ROOT_PATH = System.getProperty("user.dir");
    public final static String IMG_URL = "http://localhost:6001/admin/img/";

    public final static List<String> ORDER_STAT_COLUMNS = Arrays.asList("date", "orders", "customers", "amount", "pcr");
    public final static List<String> GOODS_STAT_COLUMNS = Arrays.asList("date", "orders", "products", "amount");
    public final static List<String> USER_STAT_COLUMNS = Arrays.asList("date", "users");

}
