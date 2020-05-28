package com.xjtuse.mall.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.xjtuse.mall.bean.goods.*;
import com.xjtuse.mall.mapper.admin.GoodsMapper;
import com.xjtuse.mall.mapper.admin.MallMapper;
import com.xjtuse.mall.result.MapResultVo;
import com.xjtuse.mall.result.ResultVo;
import com.xjtuse.mall.service.admin.GoodsService;
import com.xjtuse.mall.utils.ResultUtil;
import com.xjtuse.mall.utils.PageUtil;
import com.xjtuse.mall.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 失了秩
 * @date 2020/3/12 22:59
 * @description
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper mapper;

    @Autowired
    MallMapper mallMapper;

    @Override
    public MapResultVo queryGoods(PageUtil pageUtil, Goods goods) {
        pageUtil.initStart();
        List<Goods> data = mapper.queryGoods(pageUtil, goods);
        int count = mapper.queryGoodsCount(goods);
        return ResultUtil.ok(data, count);
    }

    @Override
    public ResultVo queryDetail(Goods goods) {
        if (goods.getId() == null) {
            return ResultUtil.fail(-1, "表单异常，id为空");
        }
        Goods goodsById = mapper.queryGoodsById(goods);
        List<GoodsAttribute> attributes = mapper.queryGoodsAttributeByConditions(goods);
        List<GoodsProduct> products = mapper.queryProductsById(goods);
        List<GoodsSpecification> specifications = mapper.queryGoodsSpecificationsById(goods);
        int id = goodsById.getCategoryId();
        int pid = mapper.queryL1CatByCid(id);
        int[] ints = {pid, id};
        Map map = new HashMap();
        map.put("categoryIds", ints);
        map.put("attributes", attributes);
        map.put("goods", goodsById);
        map.put("products", products);
        map.put("specifications", specifications);
        return ResultUtil.genSuccessResult(map);
    }

    @Override
    public MapResultVo queryCatAndBrand() {
        List brandList = mapper.queryBrandForGoods();
        List<ParentCategory> categoryList = mapper.queryL1CategoryForGoods();
        for (ParentCategory parentCategory : categoryList) {
            List categoryL2 = mapper.queryL2CategoryForGoods(parentCategory);
            parentCategory.setChildren(categoryL2);
        }
        Map map = new HashMap<>();
        map.put("brandList", brandList);
        map.put("categoryList", categoryList);
        return ResultUtil.catAndBrandOk(map);
    }

    @Override
    public ResultVo updateGoods(GoodsData data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (String s1 : data.getGoods().getGallery()) {
            stringBuilder.append(s1);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        mapper.updateGoods(data.getGoods(), stringBuilder.toString());
        mapper.setAttributeDeleted(data.getGoods().getId());
        for (GoodsAttribute attribute : data.getAttributes()) {
            if (attribute.getId() == null) {
                mapper.insertAttribute(attribute, data.getGoods().getId());
            } else {
                mapper.updateAttribute(attribute);
            }
        }

        mapper.setProductDeleted(data.getGoods().getId());
        List<GoodsProduct> products = data.getProducts();
        for (GoodsProduct product : products) {
            if (product.getGoodsId() == null) {
                mapper.insertProduct(product, data.getGoods().getId(), JSON.toJSONString(product.getSpecifications()));
            } else {
                mapper.updateProducts(product, JSON.toJSONString(product.getSpecifications()));
            }
        }

        mapper.setSpecificationsDeleted(data.getGoods().getId());
        List<GoodsSpecification> specifications = data.getSpecifications();
        for (GoodsSpecification specification : specifications) {
            if (specification.getGoodsId() == null) {
                mapper.insertSpecification(specification, data.getGoods().getId());
            } else {
                mapper.updateSpecification(specification);
            }
        }
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPage(1);
        pageUtil.setLimit(20);
        pageUtil.setOrder("desc");
        pageUtil.setSort("add_time");
        pageUtil.initStart();
        return this.queryGoods(pageUtil, null);
    }

    @Override
    public ResultVo goodsDelete(Goods goods) {
        mapper.setGoodsDeleted(goods.getId());
        mapper.setAttributeDeleted(goods.getId());
        mapper.setProductDeleted(goods.getId());
        mapper.setSpecificationsDeleted(goods.getId());
        return ResultUtil.genSuccessResult();
    }

    @Override
    public ResultVo goodsCreate(GoodsData data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (String s1 : data.getGoods().getGallery()) {
            stringBuilder.append("\"");
            stringBuilder.append(s1);
            stringBuilder.append("\",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        Goods goods = data.getGoods();
        goods.setGoodsSn(UUIDUtils.getUUID());
        mapper.insertGoods(data.getGoods(), stringBuilder.toString());
        Integer id = mapper.queryMaxId() + 1;

        for (GoodsAttribute attribute : data.getAttributes()) {
            mapper.insertAttribute(attribute, id);
        }

        for (GoodsSpecification specification : data.getSpecifications()) {
            mapper.insertSpecification(specification, id);
        }

        for (GoodsProduct product : data.getProducts()) {
            mapper.insertProduct(product, id, stringBuilder.toString());
        }
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPage(1);
        pageUtil.setLimit(20);
        pageUtil.setOrder("desc");
        pageUtil.setSort("add_time");
        pageUtil.initStart();
        return this.queryGoods(pageUtil, null);
    }

    @Override
    public ResultVo commentList(PageUtil pageUtil, Comment comment) {
        pageUtil.initStart();
        List<Comment> comments = mapper.queryCommentList(pageUtil, comment);
        int total = mapper.queryCommentTotal(comment);
        Map map = new HashMap();
        map.put("items", comments);
        map.put("total", total);
        return ResultUtil.genSuccessResult(map);
    }

    @Override
    public ResultVo commentDelet(Comment comment) {
        mapper.setCommentDeleted(comment);
        return ResultUtil.genSuccessResult();
    }
}
