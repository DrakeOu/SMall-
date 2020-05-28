package com.xjtuse.mall.bean.user;


import java.sql.Timestamp;

public class Collect {

  private Integer id;
  private Integer userId;
  private Integer valueId;
  private Integer type;
  private java.sql.Timestamp addTime;
  private java.sql.Timestamp updateTime;
  private Integer deleted;

  @Override
  public String toString() {
    return "Collect{" +
            "id=" + id +
            ", userId=" + userId +
            ", valueId=" + valueId +
            ", type=" + type +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
            ", deleted=" + deleted +
            '}';
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

  public Integer getValueId() {
    return valueId;
  }

  public void setValueId(Integer valueId) {
    this.valueId = valueId;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
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

  public long getId() {
    return id;
  }


}
