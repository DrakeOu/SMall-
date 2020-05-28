package com.xjtuse.mall.result;

import java.util.List;
import java.util.Map;

public class MapResultVo extends ResultVo {
    private Map data;

    @Override
    public String toString() {
        return "MapResultVo{" +
                "data=" + data +
                ", errmsg='" + errmsg + '\'' +
                ", errno=" + errno +
                '}';
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
