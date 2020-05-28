package com.xjtuse.mall.bean.user;


import java.sql.Timestamp;

public class Address {

  private int id;
  private String name;
  private Integer userId;
  private int provinceId;
  private int cityId;
  private int areaId;
  private String address;
  private String mobile;
  private int isDefault;
  private java.sql.Timestamp addTime;
  private java.sql.Timestamp updateTime;
  private int deleted;

  @Override
  public String toString() {
    return "Address{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", userId=" + userId +
            ", provinceId=" + provinceId +
            ", cityId=" + cityId +
            ", areaId=" + areaId +
            ", address='" + address + '\'' +
            ", mobile='" + mobile + '\'' +
            ", isDefault=" + isDefault +
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getProvinceId() {
    return provinceId;
  }

  public void setProvinceId(Integer provinceId) {
    this.provinceId = provinceId;
  }

  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  public Integer getAreaId() {
    return areaId;
  }

  public void setAreaId(Integer areaId) {
    this.areaId = areaId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Integer getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(Integer isDefault) {
    this.isDefault = isDefault;
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
