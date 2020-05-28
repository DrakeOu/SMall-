package com.xjtuse.mall.bean.promotion;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xjtuse.mall.common.CustomJsonDateDeserializer;

import java.math.BigDecimal;
import java.util.Date;

public class GrouponRules {

    private Integer id;
    private Integer goodsId;
    private String goodsName;
    private String picUrl;
    private BigDecimal discount;
    private Integer discountMember;
    private Date addTime;
    private Date updateTime;
    private Date expireTime;
    private Boolean deleted;

    @Override
    public String toString() {
        return "GrouponRules{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", discount=" + discount +
                ", discountMember=" + discountMember +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", expireTime=" + expireTime +
                ", deleted=" + deleted +
                '}';
    }

    public GrouponRules() {
    }

    public GrouponRules(Integer id, Integer goodsId, String goodsName, String picUrl, BigDecimal discount, Integer discountMember, Date addTime, Date updateTime, Date expireTime, Boolean deleted) {
        this.id = id;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.picUrl = picUrl;
        this.discount = discount;
        this.discountMember = discountMember;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.expireTime = expireTime;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getDiscountMember() {
        return discountMember;
    }

    public void setDiscountMember(Integer discountMember) {
        this.discountMember = discountMember;
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

    public Date getExpireTime() {
        return expireTime;
    }

    //注释用于解析多种json日期格式
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
