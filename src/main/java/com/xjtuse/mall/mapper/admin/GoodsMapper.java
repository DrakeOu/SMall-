package com.xjtuse.mall.mapper.admin;

import com.xjtuse.mall.bean.goods.*;
import com.xjtuse.mall.utils.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {

    List<Goods> queryGoods(@Param("pageUtil") PageUtil pageUtil,@Param("goods") Goods goods);
    int queryGoodsCount(@Param("goods") Goods goods);

    List queryBrandForGoods();
    List queryL1CategoryForGoods();
    List queryL2CategoryForGoods(@Param("parentCategory") ParentCategory parentCategory);

    Goods queryGoodsById(@Param("goods") Goods goods);

    List<GoodsAttribute> queryGoodsAttributeByConditions(@Param("goods") Goods goods);

    List<GoodsProduct> queryProductsById(@Param("goods") Goods goods);

    List<GoodsSpecification> queryGoodsSpecificationsById(@Param("goods") Goods goods);

    int queryL1CatByCid(Integer categoryId);

    void updateGoods(@Param("goods") Goods goods,@Param("gallery") String gallery);


    void setAttributeDeleted(@Param("goodsId") Integer id);
    void updateAttribute(@Param("attribute") GoodsAttribute attributes);
    void insertAttribute(@Param("attribute") GoodsAttribute attribute, @Param("goodsId") Integer goodsId);


    void setProductDeleted(@Param("goodsId") Integer id);
    void insertProduct(@Param("product") GoodsProduct product,@Param("goodsId") Integer id,@Param("specifications") String specifications);
    void updateProducts(@Param("product") GoodsProduct product,@Param("specifications") String specifications);



    void setSpecificationsDeleted(@Param("goodsId") Integer id);
    void insertSpecification(@Param("specification") GoodsSpecification specification,@Param("goodsId") Integer id);
    void updateSpecification(@Param("specification") GoodsSpecification specification);

    void setGoodsDeleted(@Param("id") Integer id);

    Integer insertGoods(@Param("goods") Goods goods, @Param("gallery") String gallery);

    Integer queryMaxId();

    List<Comment> queryCommentList(@Param("pageUtil") PageUtil pageUtil,@Param("comment") Comment comment);

    int queryCommentTotal(@Param("comment") Comment comment);

    void setCommentDeleted(@Param("comment") Comment comment);
}
