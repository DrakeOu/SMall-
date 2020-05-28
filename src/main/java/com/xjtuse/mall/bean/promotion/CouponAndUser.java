package com.xjtuse.mall.bean.promotion;

import java.util.Date;

public class CouponAndUser {
    private Integer id;
    private Integer userId;
    private Integer couponId;
    private Integer status;
    private Date usedTime;
    private Date startTime;
    private Date endTime;
    private Integer orderId;
    private Date addTime;
    private Date updateTime;
    private Boolean deleted;

    @Override
    public String toString() {
        return "CouponAndUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", couponId=" + couponId +
                ", status=" + status +
                ", usedTime=" + usedTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", orderId=" + orderId +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }

    public CouponAndUser() {
    }

    public CouponAndUser(Integer id, Integer userId, Integer couponId, Integer status, Date usedTime, Date startTime, Date endTime, Integer orderId, Date addTime, Date updateTime, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.couponId = couponId;
        this.status = status;
        this.usedTime = usedTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderId = orderId;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
