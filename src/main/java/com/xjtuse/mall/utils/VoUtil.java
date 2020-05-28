package com.xjtuse.mall.utils;

import com.xjtuse.mall.VO.*;
import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.goods.GoodsAttribute;
import com.xjtuse.mall.bean.goods.GoodsProduct;
import com.xjtuse.mall.bean.goods.GoodsSpecification;
import com.xjtuse.mall.bean.mall.Brand;
import com.xjtuse.mall.bean.mall.Category;
import com.xjtuse.mall.bean.mall.Issue;
import com.xjtuse.mall.bean.promotion.Ad;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.promotion.Groupon;
import com.xjtuse.mall.bean.promotion.Topic;
import com.xjtuse.mall.bean.user.User;

import java.util.HashMap;
import java.util.List;

public class VoUtil {

    public static Object genUserInfo(User user, String token){
        HashMap<String, Object> map = new HashMap<>();
        map.put("userInfo", new UserInfoVo(user));
        map.put("token", token);
        return map;
    }


    public static Object genIndexVo(List<Coupon> couponList, List<Category> categoryList, List<Groupon> grouponList, List<Ad> adList, List<Brand> brandList, List<Goods> goodsList, List<Topic> topicList, List<Goods> newGoods){
        IndexVo indexVo = new IndexVo();
        indexVo.setBanner(adList);
        indexVo.setBrandList(brandList);
        indexVo.setChannel(categoryList);
        indexVo.setCouponList(couponList);
        indexVo.setGrouponList(grouponList);
        indexVo.setHotGoodsList(goodsList);
        indexVo.setNewGoodsList(newGoods);
        indexVo.setTopicList(topicList);

        return indexVo;
    }

    public static Object genGoodsVo(Integer goodsCount, Integer pages, Integer limit, Integer page, List<Goods> goodsList){
        GoodsVo goodsVo = new GoodsVo();
        goodsVo.setLimit(limit);
        goodsVo.setList(goodsList);
        goodsVo.setPage(page);
        goodsVo.setPages(pages);
        goodsVo.setTotal(goodsCount);

        return goodsVo;
    }

    public static Object genGoodsVo(List<Goods> goodsList){
        GoodsVo goodsVo = new GoodsVo();

        goodsVo.setList(goodsList);
        return goodsVo;
    }


    public static Object genDetailVo(List<GoodsSpecification> specificationList, List<Issue> issueList, Integer collectCount, List<GoodsProduct> productList, List<GoodsAttribute> attributeList, Goods goods1) {
        DetailVo detailVo = new DetailVo();

        detailVo.setSpecificationList(specificationList);
        detailVo.setIssue(issueList);
        detailVo.setUserHasCollect(collectCount);
        detailVo.setProductList(productList);
        detailVo.setInfo(goods1);
        detailVo.setAttribute(attributeList);

        return detailVo;
    }


    public static <T> Object genListVo(Integer count, Integer pages, Integer limit, Integer page, List<T> list){
        ListVo listVo = new ListVo();

        listVo.setTotal(count);
        listVo.setPages(pages);
        listVo.setPage(page);
        listVo.setLimit(limit);
        listVo.setList(list);
        return listVo;
    }


    public static Object genTopicDetailVo(Topic topic1, String[] goods) {
        TopicDetailVo topicDetailVo = new TopicDetailVo();
        topicDetailVo.setGoods(goods);
        topicDetailVo.setTopic(topic1);
        return topicDetailVo;
    }

    public static Object genCategoryVo(List<Category> categoryList, Category currentCategory, List<Category> subCategoryList) {
        CategoryVo categoryVo = new CategoryVo();

        categoryVo.setCategoryList(categoryList);
        categoryVo.setCurrentCategory(currentCategory);
        categoryVo.setCurrentSubCategory(subCategoryList);
        return categoryVo;
    }
}
