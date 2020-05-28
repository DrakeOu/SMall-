package com.xjtuse.mall.bean.promotion;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class Topic {
    private Integer id;
    private String title;
    private String subtitle;
    private String content;
    private BigDecimal price;
    private String readCount;
    private String picUrl;
    private Integer sortOrder;
    private String[] goods;
    private Date addTime;
    private Date updateTime;
    private Boolean deleted;

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", readCount='" + readCount + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", sortOrder=" + sortOrder +
                ", goods=" + Arrays.toString(goods) +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }

    public Topic() {
    }

    public Topic(Integer id, String title, String subtitle, String content, BigDecimal price, String readCount, String picUrl, Integer sortOrder, String[] goods, Date addTime, Date updateTime, Boolean deleted) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.price = price;
        this.readCount = readCount;
        this.picUrl = picUrl;
        this.sortOrder = sortOrder;
        this.goods = goods;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String[] getGoods() {
        return goods;
    }

    public void setGoods(String[] goods) {
        this.goods = goods;
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
