package com.xjtuse.mall.mapper.wx;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.goods.GoodsAttribute;
import com.xjtuse.mall.bean.goods.GoodsProduct;
import com.xjtuse.mall.bean.goods.GoodsSpecification;
import com.xjtuse.mall.bean.mall.Brand;
import com.xjtuse.mall.bean.mall.Category;
import com.xjtuse.mall.utils.PageUtil;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface WxGoodsMapper {
    List<Category> queryPriCate();

    List<Brand> queryCountBrand(int count);

    List<Goods> queryCountHotest(int count);

    List<Goods> queryCountNewGoods(int count);

    List<Goods> queryGoodsList(@Param("pageUtil") PageUtil pageUtil, @Param("goods") Goods goods);

    Integer countGoods();

    List<GoodsSpecification> querySpecificationById(Goods goods);

    List<GoodsProduct> queryProductByGoods(Goods goods);

    List<GoodsAttribute> queryAttribute(Goods goods);

    Goods queryCate(Goods goods);

    Integer queryCountCate(Goods goods1);

    Integer queryCountGoods();

    Integer queryBrandCount();

    List<Brand> queryBrandList(@Param("brand") Brand brand);

    List<Category> queryL1();

    Category findCateById(Integer id);

    List<Category> queryCateByPid(Integer id);

    Goods queryGoodsById(Integer goodsId);

    boolean isOnSale(Goods goods);


    /**
     * 此mapper及对应service 控制如下 表数据
     * brand
     * category
     * goods
     * goods_specification
     * goods_attribute
     * goods_product
     * order_goods
     */

}
