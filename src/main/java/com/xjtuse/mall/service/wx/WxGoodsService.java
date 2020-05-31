package com.xjtuse.mall.service.wx;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.goods.GoodsAttribute;
import com.xjtuse.mall.bean.goods.GoodsProduct;
import com.xjtuse.mall.bean.goods.GoodsSpecification;
import com.xjtuse.mall.bean.mall.Brand;
import com.xjtuse.mall.bean.mall.Category;
import com.xjtuse.mall.bean.mall.OrderAndGoods;
import com.xjtuse.mall.utils.PageUtil;

import java.util.List;

public interface WxGoodsService {
    List<Category> queryPriCate();

    List<Brand> queryCountBrand(int count);

    List<Goods> queryCountHotest(int count);

    List<Goods> queryCountNewGoods(int count);

    List<Goods> queryGoodsList(PageUtil pageUtil, Goods goods);

    Integer countGoods();

    List<GoodsSpecification> querySpecificationById(Goods goods);

    List<GoodsProduct> queryProductByGoods(Goods goods);

    List<GoodsAttribute> queryAttribute(Goods goods);

    Goods queryGoodsCategory(Goods goods);

    Integer queryCountCate(Goods goods1);

    Integer queryCountGoods();

    Integer queryBrandCount();

    List<Brand> queryBrandList(Brand brand);

    List<Category> queryL1();

    Category findCateById(Integer id);

    List<Category> queryCateByPid(Integer id);

    Goods queryGoodsById(Integer goodsId);

    boolean isOnSale(Goods goods);

    GoodsProduct queryProductByPid(Integer productId);

    void addOrderAndGoods(OrderAndGoods orderGoods);

    int reduceStock(Integer productId, Short number);

    List<OrderAndGoods> queryOrderGoodsList(Integer orderId);

    int addStock(Integer productId, Short number);

    List<OrderAndGoods> queryOrderAndGoodsByOid(Integer id);

    OrderAndGoods queryOrderGoodsByid(Integer orderGoodsId);

    Short getComments(Integer orderId);
}
