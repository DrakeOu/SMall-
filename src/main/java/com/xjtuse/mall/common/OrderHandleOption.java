package com.xjtuse.mall.common;

public class OrderHandleOption {

    private boolean cancel = false;
    private boolean delete = false;
    private boolean pay = false;
    private boolean comment = false;
    private boolean confirm = false;
    private boolean refund = false;
    private boolean rebuy = false;
    private boolean aftersale = false;

    public OrderHandleOption() {
    }

    public boolean isCancel() {
        return this.cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public boolean isDelete() {
        return this.delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isPay() {
        return this.pay;
    }

    public void setPay(boolean pay) {
        this.pay = pay;
    }

    public boolean isComment() {
        return this.comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isConfirm() {
        return this.confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public boolean isRefund() {
        return this.refund;
    }

    public void setRefund(boolean refund) {
        this.refund = refund;
    }

    public boolean isRebuy() {
        return this.rebuy;
    }

    public void setRebuy(boolean rebuy) {
        this.rebuy = rebuy;
    }

    public boolean isAftersale() {
        return this.aftersale;
    }

    public void setAftersale(boolean aftersale) {
        this.aftersale = aftersale;
    }
}
