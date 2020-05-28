package com.xjtuse.mall.result;

import java.util.List;

public class ListResultVo extends ResultVo {
    List data;

    @Override
    public String toString() {
        return "ListResultVo{" +
                "data=" + data +
                ", errmsg='" + errmsg + '\'' +
                ", errno=" + errno +
                '}';
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
