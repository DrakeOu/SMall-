package com.xjtuse.mall.bean.mall;

import org.omg.PortableInterceptor.INACTIVE;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer id;
    private Integer userId;
    private String orderSn;
    private Integer orderStatus;
    private String consignee;
    private String mobile;
    private String address;
    private String message;
    private BigDecimal goodsPrice;
    private BigDecimal freightPrice;
    private BigDecimal couponPrice;
    private BigDecimal integralPrice;
    private BigDecimal grouponPrice;
    private BigDecimal orderPrice;
    private BigDecimal actualPrice;
    private String payId;
    private Date payTime;
    private String shipSn;
    private String shipChannel;
    private Date shipTime;
    private Date confirmTime;
    private Integer comments;
    private Date endTime;
    private Date addTime;
    private Date updateTime;
    private Boolean deleted;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderSn='" + orderSn + '\'' +
                ", orderStatus=" + orderStatus +
                ", consignee='" + consignee + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", message='" + message + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", freightPrice=" + freightPrice +
                ", couponPrice=" + couponPrice +
                ", integralPrice=" + integralPrice +
                ", grouponPrice=" + grouponPrice +
                ", orderPrice=" + orderPrice +
                ", actualPrice=" + actualPrice +
                ", payId='" + payId + '\'' +
                ", payTime=" + payTime +
                ", shipSn='" + shipSn + '\'' +
                ", shipChannel='" + shipChannel + '\'' +
                ", shipTime=" + shipTime +
                ", confirmTime=" + confirmTime +
                ", comments=" + comments +
                ", endTime=" + endTime +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }

    public Order() {
    }

    public Order(Integer id, Integer userId, String orderSn, Integer orderStatus, String consignee, String mobile, String address, String message, BigDecimal goodsPrice, BigDecimal freightPrice, BigDecimal couponPrice, BigDecimal integralPrice, BigDecimal grouponPrice, BigDecimal orderPrice, BigDecimal actualPrice, String payId, Date payTime, String shipSn, String shipChannel, Date shipTime, Date confirmTime, Integer comments, Date endTime, Date addTime, Date updateTime, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.orderSn = orderSn;
        this.orderStatus = orderStatus;
        this.consignee = consignee;
        this.mobile = mobile;
        this.address = address;
        this.message = message;
        this.goodsPrice = goodsPrice;
        this.freightPrice = freightPrice;
        this.couponPrice = couponPrice;
        this.integralPrice = integralPrice;
        this.grouponPrice = grouponPrice;
        this.orderPrice = orderPrice;
        this.actualPrice = actualPrice;
        this.payId = payId;
        this.payTime = payTime;
        this.shipSn = shipSn;
        this.shipChannel = shipChannel;
        this.shipTime = shipTime;
        this.confirmTime = confirmTime;
        this.comments = comments;
        this.endTime = endTime;
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

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(BigDecimal integralPrice) {
        this.integralPrice = integralPrice;
    }

    public BigDecimal getGrouponPrice() {
        return grouponPrice;
    }

    public void setGrouponPrice(BigDecimal grouponPrice) {
        this.grouponPrice = grouponPrice;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getShipSn() {
        return shipSn;
    }

    public void setShipSn(String shipSn) {
        this.shipSn = shipSn;
    }

    public String getShipChannel() {
        return shipChannel;
    }

    public void setShipChannel(String shipChannel) {
        this.shipChannel = shipChannel;
    }

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
