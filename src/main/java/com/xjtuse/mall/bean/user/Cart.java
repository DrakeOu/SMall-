package com.xjtuse.mall.bean.user;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Cart {
    public static final Boolean IS_DELETED;
    public static final Boolean NOT_DELETED;
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private String goodsSn;
    private String goodsName;
    private Integer productId;
    private BigDecimal price;
    private Short number;
    private String[] specifications;
    private Boolean checked;
    private String picUrl;
    private LocalDateTime addTime;
    private LocalDateTime updateTime;
    private Boolean deleted;

    public Cart() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSn() {
        return this.goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Short getNumber() {
        return this.number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public String[] getSpecifications() {
        return this.specifications;
    }

    public void setSpecifications(String[] specifications) {
        this.specifications = specifications;
    }

    public Boolean getChecked() {
        return this.checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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
        this.setDeleted(deleted ? Cart.Deleted.IS_DELETED.value() : Cart.Deleted.NOT_DELETED.value());
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
        sb.append(", userId=").append(this.userId);
        sb.append(", goodsId=").append(this.goodsId);
        sb.append(", goodsSn=").append(this.goodsSn);
        sb.append(", goodsName=").append(this.goodsName);
        sb.append(", productId=").append(this.productId);
        sb.append(", price=").append(this.price);
        sb.append(", number=").append(this.number);
        sb.append(", specifications=").append(this.specifications);
        sb.append(", checked=").append(this.checked);
        sb.append(", picUrl=").append(this.picUrl);
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
            label141: {
                label132: {
                    Cart other = (Cart)that;
                    if (this.getId() == null) {
                        if (other.getId() != null) {
                            break label132;
                        }
                    } else if (!this.getId().equals(other.getId())) {
                        break label132;
                    }

                    if (this.getUserId() == null) {
                        if (other.getUserId() != null) {
                            break label132;
                        }
                    } else if (!this.getUserId().equals(other.getUserId())) {
                        break label132;
                    }

                    if (this.getGoodsId() == null) {
                        if (other.getGoodsId() != null) {
                            break label132;
                        }
                    } else if (!this.getGoodsId().equals(other.getGoodsId())) {
                        break label132;
                    }

                    if (this.getGoodsSn() == null) {
                        if (other.getGoodsSn() != null) {
                            break label132;
                        }
                    } else if (!this.getGoodsSn().equals(other.getGoodsSn())) {
                        break label132;
                    }

                    if (this.getGoodsName() == null) {
                        if (other.getGoodsName() != null) {
                            break label132;
                        }
                    } else if (!this.getGoodsName().equals(other.getGoodsName())) {
                        break label132;
                    }

                    if (this.getProductId() == null) {
                        if (other.getProductId() != null) {
                            break label132;
                        }
                    } else if (!this.getProductId().equals(other.getProductId())) {
                        break label132;
                    }

                    if (this.getPrice() == null) {
                        if (other.getPrice() != null) {
                            break label132;
                        }
                    } else if (!this.getPrice().equals(other.getPrice())) {
                        break label132;
                    }

                    if (this.getNumber() == null) {
                        if (other.getNumber() != null) {
                            break label132;
                        }
                    } else if (!this.getNumber().equals(other.getNumber())) {
                        break label132;
                    }

                    if (Arrays.equals(this.getSpecifications(), other.getSpecifications())) {
                        label136: {
                            if (this.getChecked() == null) {
                                if (other.getChecked() != null) {
                                    break label136;
                                }
                            } else if (!this.getChecked().equals(other.getChecked())) {
                                break label136;
                            }

                            if (this.getPicUrl() == null) {
                                if (other.getPicUrl() != null) {
                                    break label136;
                                }
                            } else if (!this.getPicUrl().equals(other.getPicUrl())) {
                                break label136;
                            }

                            if (this.getAddTime() == null) {
                                if (other.getAddTime() != null) {
                                    break label136;
                                }
                            } else if (!this.getAddTime().equals(other.getAddTime())) {
                                break label136;
                            }

                            if (this.getUpdateTime() == null) {
                                if (other.getUpdateTime() != null) {
                                    break label136;
                                }
                            } else if (!this.getUpdateTime().equals(other.getUpdateTime())) {
                                break label136;
                            }

                            if (this.getDeleted() == null) {
                                if (other.getDeleted() == null) {
                                    break label141;
                                }
                            } else if (this.getDeleted().equals(other.getDeleted())) {
                                break label141;
                            }
                        }
                    }
                }

                var10000 = false;
                return var10000;
            }

            var10000 = true;
            return var10000;
        }
    }

    static {
        IS_DELETED = Cart.Deleted.IS_DELETED.value();
        NOT_DELETED = Cart.Deleted.NOT_DELETED.value();
    }

    public static enum Column {
        id("id", "id", "INTEGER", false),
        userId("user_id", "userId", "INTEGER", false),
        goodsId("goods_id", "goodsId", "INTEGER", false),
        goodsSn("goods_sn", "goodsSn", "VARCHAR", false),
        goodsName("goods_name", "goodsName", "VARCHAR", false),
        productId("product_id", "productId", "INTEGER", false),
        price("price", "price", "DECIMAL", false),
        number("number", "number", "SMALLINT", true),
        specifications("specifications", "specifications", "VARCHAR", false),
        checked("checked", "checked", "BIT", true),
        picUrl("pic_url", "picUrl", "VARCHAR", false),
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

        public static Cart.Column[] excludes(Cart.Column... excludes) {
            ArrayList<Cart.Column> columns = new ArrayList(Arrays.asList(values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList(Arrays.asList(excludes)));
            }

            return (Cart.Column[])columns.toArray(new Cart.Column[0]);
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
