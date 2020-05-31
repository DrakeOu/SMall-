package com.xjtuse.mall.service.wx.impl;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.goods.GoodsAttribute;
import com.xjtuse.mall.bean.goods.GoodsProduct;
import com.xjtuse.mall.bean.goods.GoodsSpecification;
import com.xjtuse.mall.bean.mall.Brand;
import com.xjtuse.mall.bean.mall.Category;
import com.xjtuse.mall.bean.mall.OrderAndGoods;
import com.xjtuse.mall.mapper.wx.WxGoodsMapper;
import com.xjtuse.mall.service.wx.WxGoodsService;
import com.xjtuse.mall.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxGoodsServiceImpl implements WxGoodsService {

    @Autowired
    WxGoodsMapper goodsMapper;

    @Override
    public List<Category> queryPriCate() {
        return goodsMapper.queryPriCate();
    }

    @Override
    public List<Brand> queryCountBrand(int count) {
        return goodsMapper.queryCountBrand(count);
    }

    @Override
    public List<Goods> queryCountHotest(int count) {
        return goodsMapper.queryCountHotest(count);
    }

    @Override
    public List<Goods> queryCountNewGoods(int count) {
        return goodsMapper.queryCountNewGoods(count);
    }

    @Override
    public List<Goods> queryGoodsList(PageUtil pageUtil, Goods goods) {
        if (pageUtil != null && pageUtil.getLimit() != null) {
            pageUtil.initStart();
        }
        return goodsMapper.queryGoodsList(pageUtil, goods);
    }

    @Override
    public Integer countGoods() {
        return goodsMapper.countGoods();
    }

    @Override
    public List<GoodsSpecification> querySpecificationById(Goods goods) {
        return goodsMapper.querySpecificationById(goods);
    }

    @Override
    public List<GoodsProduct> queryProductByGoods(Goods goods) {
        return goodsMapper.queryProductByGoods(goods);
    }

    @Override
    public List<GoodsAttribute> queryAttribute(Goods goods) {
        return goodsMapper.queryAttribute(goods);
    }

    @Override
    public Goods queryGoodsCategory(Goods goods) {
        return goodsMapper.queryCate(goods);
    }

    @Override
    public Integer queryCountCate(Goods goods1) {
        return goodsMapper.queryCountCate(goods1);
    }

    @Override
    public Integer queryCountGoods() {
        return goodsMapper.queryCountGoods();
    }

    @Override
    public Integer queryBrandCount() {
        return goodsMapper.queryBrandCount();
    }

    @Override
    public List<Brand> queryBrandList(Brand brand) {
        return goodsMapper.queryBrandList(brand);
    }

    @Override
    public List<Category> queryL1() {
        return goodsMapper.queryL1();
    }

    @Override
    public Category findCateById(Integer id) {
        return goodsMapper.findCateById(id);
    }

    @Override
    public List<Category> queryCateByPid(Integer id) {
        return goodsMapper.queryCateByPid(id);
    }

    @Override
    public Goods queryGoodsById(Integer goodsId) {
        return goodsMapper.queryGoodsById(goodsId);
    }

    @Override
    public boolean isOnSale(Goods goods) {
        return goodsMapper.isOnSale(goods);
    }

    @Override
    public GoodsProduct queryProductByPid(Integer productId) {
        return goodsMapper.queryProductByPid(productId);
    }

    @Override
    public void addOrderAndGoods(OrderAndGoods orderGoods) {
        goodsMapper.addOrderAndGoods(orderGoods);
    }

    @Override
    public int reduceStock(Integer productId, Short number) {
        return goodsMapper.reduceStock(productId, number);
    }

    @Override
    public List<OrderAndGoods> queryOrderGoodsList(Integer orderId) {
        return goodsMapper.queryOrderAndGoodsByOid(orderId);
    }

    @Override
    public int addStock(Integer productId, Short number) {
        return goodsMapper.addStock(productId, number);
    }

    @Override
    public List<OrderAndGoods> queryOrderAndGoodsByOid(Integer id) {
        return goodsMapper.queryOrderAndGoodsByOid(id);
    }

    @Override
    public OrderAndGoods queryOrderGoodsByid(Integer orderGoodsId) {
        return goodsMapper.queryOrderGoodsById(orderGoodsId);
    }

    @Override
    public Short getComments(Integer orderId) {
        return goodsMapper.getComments(orderId);
    }

}
