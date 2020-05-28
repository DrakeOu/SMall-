package com.xjtuse.mall.result;

import java.io.Serializable;

public class TResultVo<T> extends ResultVo implements Serializable{
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
