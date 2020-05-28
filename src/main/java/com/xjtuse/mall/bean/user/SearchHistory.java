package com.xjtuse.mall.bean.user;


import java.sql.Timestamp;

public class SearchHistory {

  private Integer id;
  private Integer userId;
  private String keyword;
  private String from;
  private java.sql.Timestamp addTime;
  private java.sql.Timestamp updateTime;
  private Integer deleted;

  @Override
  public String toString() {
    return "SearchHistory{" +
            "id=" + id +
            ", userId=" + userId +
            ", keyword='" + keyword + '\'' +
            ", from='" + from + '\'' +
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

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
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
