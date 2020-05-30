package com.xjtuse.mall.bean.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class LitemallAddress {
    public static final Boolean IS_DELETED;
    public static final Boolean NOT_DELETED;
    private Integer id;
    private String name;
    private Integer userId;
    private String province;
    private String city;
    private String county;
    private String addressDetail;
    private String areaCode;
    private String postalCode;
    private String tel;
    private Boolean isDefault;
    private LocalDateTime addTime;
    private LocalDateTime updateTime;
    private Boolean deleted;

    public LitemallAddress() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return this.county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddressDetail() {
        return this.addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAreaCode() {
        return this.areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Boolean getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public LocalDateTime getAddTime() {
        return this.addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public void andLogicalDeleted(boolean deleted) {
        this.setDeleted(deleted ? LitemallAddress.Deleted.IS_DELETED.value() : LitemallAddress.Deleted.NOT_DELETED.value());
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(this.hashCode());
        sb.append(", IS_DELETED=").append(IS_DELETED);
        sb.append(", NOT_DELETED=").append(NOT_DELETED);
        sb.append(", id=").append(this.id);
        sb.append(", name=").append(this.name);
        sb.append(", userId=").append(this.userId);
        sb.append(", province=").append(this.province);
        sb.append(", city=").append(this.city);
        sb.append(", county=").append(this.county);
        sb.append(", addressDetail=").append(this.addressDetail);
        sb.append(", areaCode=").append(this.areaCode);
        sb.append(", postalCode=").append(this.postalCode);
        sb.append(", tel=").append(this.tel);
        sb.append(", isDefault=").append(this.isDefault);
        sb.append(", addTime=").append(this.addTime);
        sb.append(", updateTime=").append(this.updateTime);
        sb.append(", deleted=").append(this.deleted);
        sb.append("]");
        return sb.toString();
    }

    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that == null) {
            return false;
        } else if (this.getClass() != that.getClass()) {
            return false;
        } else {
            boolean var10000;
            label144: {
                LitemallAddress other = (LitemallAddress)that;
                if (this.getId() == null) {
                    if (other.getId() != null) {
                        break label144;
                    }
                } else if (!this.getId().equals(other.getId())) {
                    break label144;
                }

                if (this.getName() == null) {
                    if (other.getName() != null) {
                        break label144;
                    }
                } else if (!this.getName().equals(other.getName())) {
                    break label144;
                }

                if (this.getUserId() == null) {
                    if (other.getUserId() != null) {
                        break label144;
                    }
                } else if (!this.getUserId().equals(other.getUserId())) {
                    break label144;
                }

                if (this.getProvince() == null) {
                    if (other.getProvince() != null) {
                        break label144;
                    }
                } else if (!this.getProvince().equals(other.getProvince())) {
                    break label144;
                }

                if (this.getCity() == null) {
                    if (other.getCity() != null) {
                        break label144;
                    }
                } else if (!this.getCity().equals(other.getCity())) {
                    break label144;
                }

                if (this.getCounty() == null) {
                    if (other.getCounty() != null) {
                        break label144;
                    }
                } else if (!this.getCounty().equals(other.getCounty())) {
                    break label144;
                }

                if (this.getAddressDetail() == null) {
                    if (other.getAddressDetail() != null) {
                        break label144;
                    }
                } else if (!this.getAddressDetail().equals(other.getAddressDetail())) {
                    break label144;
                }

                if (this.getAreaCode() == null) {
                    if (other.getAreaCode() != null) {
                        break label144;
                    }
                } else if (!this.getAreaCode().equals(other.getAreaCode())) {
                    break label144;
                }

                if (this.getPostalCode() == null) {
                    if (other.getPostalCode() != null) {
                        break label144;
                    }
                } else if (!this.getPostalCode().equals(other.getPostalCode())) {
                    break label144;
                }

                if (this.getTel() == null) {
                    if (other.getTel() != null) {
                        break label144;
                    }
                } else if (!this.getTel().equals(other.getTel())) {
                    break label144;
                }

                if (this.getIsDefault() == null) {
                    if (other.getIsDefault() != null) {
                        break label144;
                    }
                } else if (!this.getIsDefault().equals(other.getIsDefault())) {
                    break label144;
                }

                if (this.getAddTime() == null) {
                    if (other.getAddTime() != null) {
                        break label144;
                    }
                } else if (!this.getAddTime().equals(other.getAddTime())) {
                    break label144;
                }

                if (this.getUpdateTime() == null) {
                    if (other.getUpdateTime() != null) {
                        break label144;
                    }
                } else if (!this.getUpdateTime().equals(other.getUpdateTime())) {
                    break label144;
                }

                if (this.getDeleted() == null) {
                    if (other.getDeleted() != null) {
                        break label144;
                    }
                } else if (!this.getDeleted().equals(other.getDeleted())) {
                    break label144;
                }

                var10000 = true;
                return var10000;
            }

            var10000 = false;
            return var10000;
        }
    }

    static {
        IS_DELETED = LitemallAddress.Deleted.IS_DELETED.value();
        NOT_DELETED = LitemallAddress.Deleted.NOT_DELETED.value();
    }

    public static enum Column {
        id("id", "id", "INTEGER", false),
        name("name", "name", "VARCHAR", true),
        userId("user_id", "userId", "INTEGER", false),
        province("province", "province", "VARCHAR", false),
        city("city", "city", "VARCHAR", false),
        county("county", "county", "VARCHAR", false),
        addressDetail("address_detail", "addressDetail", "VARCHAR", false),
        areaCode("area_code", "areaCode", "CHAR", false),
        postalCode("postal_code", "postalCode", "CHAR", false),
        tel("tel", "tel", "VARCHAR", false),
        isDefault("is_default", "isDefault", "BIT", false),
        addTime("add_time", "addTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        deleted("deleted", "deleted", "BIT", false);

        private static final String BEGINNING_DELIMITER = "`";
        private static final String ENDING_DELIMITER = "`";
        private final String column;
        private final boolean isColumnNameDelimited;
        private final String javaProperty;
        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        private Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static LitemallAddress.Column[] excludes(LitemallAddress.Column... excludes) {
            ArrayList<Column> columns = new ArrayList(Arrays.asList(values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList(Arrays.asList(excludes)));
            }

            return (LitemallAddress.Column[])columns.toArray(new LitemallAddress.Column[0]);
        }

        public String getEscapedColumnName() {
            return this.isColumnNameDelimited ? "`" + this.column + "`" : this.column;
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }

    public static enum Deleted {
        NOT_DELETED(new Boolean("0"), "未删除"),
        IS_DELETED(new Boolean("1"), "已删除");

        private final Boolean value;
        private final String name;

        private Deleted(Boolean value, String name) {
            this.value = value;
            this.name = name;
        }

        public Boolean getValue() {
            return this.value;
        }

        public Boolean value() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }
    }
}
