package com.xjtuse.mall.bean.promotion;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xjtuse.mall.common.CustomJsonDateDeserializer;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class Coupon {

    private Integer id;
    private String name;
    private String desc;
    private String tag;
    private Integer total;
    private BigDecimal discount;
    private BigDecimal min;
    private Integer limit;
    private Integer type;
    private Integer status;
    private Integer goodsType;
    private String[] goodsValue;
    private String code;
    private Integer timeType;
    private Integer days;
    private Date startTime;
    private Date endTime;
    private Date addTime;
    private Date updateTime;
    private Boolean deleted;

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", tag='" + tag + '\'' +
                ", total=" + total +
                ", discount=" + discount +
                ", min=" + min +
                ", limit=" + limit +
                ", type=" + type +
                ", status=" + status +
                ", goodsType=" + goodsType +
                ", goodsValue=" + Arrays.toString(goodsValue) +
                ", code='" + code + '\'' +
                ", timeType=" + timeType +
                ", days=" + days +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }

    public Coupon() {
    }

    public Coupon(Integer id, String name, String desc, String tag, Integer total, BigDecimal discount, BigDecimal min, Integer limit, Integer type, Integer status, Integer goodsType, String[] goodsValue, String code, Integer timeType, Integer days, Date startTime, Date endTime, Date addTime, Date updateTime, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.tag = tag;
        this.total = total;
        this.discount = discount;
        this.min = min;
        this.limit = limit;
        this.type = type;
        this.status = status;
        this.goodsType = goodsType;
        this.goodsValue = goodsValue;
        this.code = code;
        this.timeType = timeType;
        this.days = days;
        this.startTime = startTime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String[] getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(String[] goodsValue) {
        this.goodsValue = goodsValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Date getStartTime() {
        return startTime;
    }

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
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
