package com.xjtuse.mall.customException;

public class CustomException extends Exception {

    private static final long serialVersionUID = -2420754730135049197L;
    private Object data;

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
