package com.xjtuse.mall.bean.promotion;

import java.util.Date;

public class Groupon {
    private Integer id;
    private Integer orderId;
    private Integer grouponId;
    private Integer rulesId;
    private Integer userId;
    private Integer creatorUserId;
    private Date addTime;
    private Date updateTime;
    private String shareUrl;
    private Boolean payed;
    private Boolean deleted;
    private Short status;
    private Date creatorUserTime;

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreatorUserTime() {
        return creatorUserTime;
    }

    public void setCreatorUserTime(Date creatorUserTime) {
        this.creatorUserTime = creatorUserTime;
    }

    public Groupon() {
    }

    @Override
    public String toString() {
        return "Groupon{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", grouponId=" + grouponId +
                ", rulesId=" + rulesId +
                ", userId=" + userId +
                ", creatorUserId=" + creatorUserId +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", shareUrl='" + shareUrl + '\'' +
                ", payed=" + payed +
                ", deleted=" + deleted +
                '}';
    }

    public Groupon(Integer id, Integer orderId, Integer grouponId, Integer rulesId, Integer userId, Integer creatorUserId, Date addTime, Date updateTime, String shareUrl, Boolean payed, Boolean deleted) {
        this.id = id;
        this.orderId = orderId;
        this.grouponId = grouponId;
        this.rulesId = rulesId;
        this.userId = userId;
        this.creatorUserId = creatorUserId;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.shareUrl = shareUrl;
        this.payed = payed;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGrouponId() {
        return grouponId;
    }

    public void setGrouponId(Integer grouponId) {
        this.grouponId = grouponId;
    }

    public Integer getRulesId() {
        return rulesId;
    }

    public void setRulesId(Integer rulesId) {
        this.rulesId = rulesId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Integer creatorUserId) {
        this.creatorUserId = creatorUserId;
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

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Boolean getPayed() {
        return payed;
    }

    public void setPayed(Boolean payed) {
        this.payed = payed;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
