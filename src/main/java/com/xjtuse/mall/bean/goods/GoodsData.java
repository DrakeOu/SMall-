package com.xjtuse.mall.bean.goods;

import java.util.List;

/**
 * @author 失了秩
 * @date 2020/3/15 23:54
 * @description
 */
public class GoodsData {
    Goods goods;
    List<GoodsAttribute> attributes;
    List<GoodsProduct> products;
    List<GoodsSpecification> specifications;

    public List<GoodsAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<GoodsAttribute> attributes) {
        this.attributes = attributes;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<GoodsProduct> getProducts() {
        return products;
    }

    public void setProducts(List<GoodsProduct> products) {
        this.products = products;
    }

    public List<GoodsSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<GoodsSpecification> specifications) {
        this.specifications = specifications;
    }
}
