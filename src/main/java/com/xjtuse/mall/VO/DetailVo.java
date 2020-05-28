package com.xjtuse.mall.VO;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.goods.GoodsAttribute;
import com.xjtuse.mall.bean.goods.GoodsProduct;
import com.xjtuse.mall.bean.goods.GoodsSpecification;
import com.xjtuse.mall.bean.mall.Brand;
import com.xjtuse.mall.bean.mall.Issue;
import com.xjtuse.mall.bean.promotion.Groupon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailVo {

    private class SpecificationItem{
        private String name;
        private List<GoodsSpecification> valueList;

        public SpecificationItem(String name, List<GoodsSpecification> valueList) {
            this.name = name;
            this.valueList = valueList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<GoodsSpecification> getValueList() {
            return valueList;
        }

        public void setValueList(List<GoodsSpecification> valueList) {
            this.valueList = valueList;
        }
    }

    private List<SpecificationItem> specificationList;

    private List<Groupon> groupon;

    private List<Issue> issue;

    private Integer userHasCollect;

    private String shareImage;

    private List<GoodsAttribute> attribute;

    private Brand brand;

    private List<GoodsProduct> productList;

    private Goods info;

    public List<SpecificationItem> getSpecificationList() {
        return specificationList;
    }

    public void setSpecificationList(List<GoodsSpecification> specificationList) {
        this.specificationList = new ArrayList<>();

        Map<String, List<GoodsSpecification>> work = new HashMap<>();
        for (GoodsSpecification s:
             specificationList) {
            if(work.containsKey(s.getSpecification())){
                work.get(s.getSpecification()).add(s);
            }else{
                List<GoodsSpecification> list = new ArrayList<>();
                list.add(s);
                work.put(s.getSpecification(), list);
            }
        }
        for(Map.Entry<String, List<GoodsSpecification>> entry: work.entrySet()){
            this.specificationList.add(new SpecificationItem(entry.getKey(), entry.getValue()));
        }
    }

    public List<Groupon> getGroupon() {
        return groupon;
    }

    public void setGroupon(List<Groupon> groupon) {
        this.groupon = groupon;
    }

    public List<Issue> getIssue() {
        return issue;
    }

    public void setIssue(List<Issue> issue) {
        this.issue = issue;
    }

    public Integer getUserHasCollect() {
        return userHasCollect;
    }

    public void setUserHasCollect(Integer userHasCollect) {
        this.userHasCollect = userHasCollect;
    }

    public String getShareImage() {
        return shareImage;
    }

    public void setShareImage(String shareImage) {
        this.shareImage = shareImage;
    }

    public List<GoodsAttribute> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<GoodsAttribute> attribute) {
        this.attribute = attribute;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<GoodsProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<GoodsProduct> productList) {
        this.productList = productList;
    }

    public Goods getInfo() {
        return info;
    }

    public void setInfo(Goods info) {
        this.info = info;
    }
}
