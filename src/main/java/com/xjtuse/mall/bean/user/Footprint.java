package com.xjtuse.mall.bean.user;


import java.sql.Timestamp;

public class Footprint {

  private Integer id;
  private Integer userId;
  private Integer goodsId;
  private java.sql.Timestamp addTime;
  private java.sql.Timestamp updateTime;
  private Integer deleted;

  @Override
  public String toString() {
    return "Footprint{" +
            "id=" + id +
            ", userId=" + userId +
            ", goodsId=" + goodsId +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
            ", deleted=" + deleted +
            '}';
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

  public Integer getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Integer goodsId) {
    this.goodsId = goodsId;
  }

  public Timestamp getAddTime() {
    return addTime;
  }

  public void setAddTime(Timestamp addTime) {
    this.addTime = addTime;
  }

  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }
}
