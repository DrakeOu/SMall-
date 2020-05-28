package com.xjtuse.mall.bean.user;


import java.sql.Timestamp;

public class Feedback {

  private Integer id;
  private Integer userId;
  private String username;
  private String mobile;
  private String feedType;
  private String content;
  private Integer status;
  private Integer hasPicture;
  private String picUrls;
  private java.sql.Timestamp addTime;
  private java.sql.Timestamp updateTime;
  private Integer deleted;


  @Override
  public String toString() {
    return "Feedback{" +
            "id=" + id +
            ", userId=" + userId +
            ", username='" + username + '\'' +
            ", mobile='" + mobile + '\'' +
            ", feedType='" + feedType + '\'' +
            ", content='" + content + '\'' +
            ", status=" + status +
            ", hasPicture=" + hasPicture +
            ", picUrls='" + picUrls + '\'' +
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getFeedType() {
    return feedType;
  }

  public void setFeedType(String feedType) {
    this.feedType = feedType;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getHasPicture() {
    return hasPicture;
  }

  public void setHasPicture(Integer hasPicture) {
    this.hasPicture = hasPicture;
  }

  public String getPicUrls() {
    return picUrls;
  }

  public void setPicUrls(String picUrls) {
    this.picUrls = picUrls;
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
