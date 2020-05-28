package com.xjtuse.mall.controller.wx;


import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.goods.GoodsAttribute;
import com.xjtuse.mall.bean.goods.GoodsProduct;
import com.xjtuse.mall.bean.goods.GoodsSpecification;
import com.xjtuse.mall.bean.mall.Brand;
import com.xjtuse.mall.bean.mall.Category;
import com.xjtuse.mall.bean.mall.Issue;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxGoodsService;
import com.xjtuse.mall.service.wx.WxOtherService;
import com.xjtuse.mall.service.wx.WxUserService;
import com.xjtuse.mall.utils.PageUtil;
import com.xjtuse.mall.utils.ResultUtil;
import com.xjtuse.mall.utils.VoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class WxGoodsController {

    @Autowired
    WxGoodsService goodsService;

    @Autowired
    WxOtherService otherService;

    @Autowired
    WxUserService userService;

    @RequestMapping("/goods/list")
    public TResultVo goodsList(PageUtil pageUtil, Goods goods){
        //查询goods数据
        List<Goods> goodsList = goodsService.queryGoodsList(pageUtil, goods);
        Integer goodsCount = null;
        Integer pages = null;
        if(pageUtil.getLimit()!=null){
            //查询全部数量
            goodsCount = goodsService.countGoods();
            //计算pages
            pages = 1 + goodsCount / pageUtil.getLimit();
        }

        if(pageUtil.getLimit()!=null){
            return ResultUtil.genSuccessResult(VoUtil.genGoodsVo(
                    goodsCount,
                    pages,
                    pageUtil.getLimit(),
                    pageUtil.getPage(),
                    goodsList
            ));
        }

        return ResultUtil.genSuccessResult(VoUtil.genGoodsVo(goodsList));
    }

    @RequestMapping("/goods/detail")
    public TResultVo goodsDetail(Goods goods){
        //查询详情列表
        List<GoodsSpecification> specificationList = goodsService.querySpecificationById(goods);
        //查询团购信息
        //查询全部issue
        List<Issue> issueList = otherService.queryAllIssue();
        //查询用户收藏数量
        Integer collectCount = userService.queryCollectCount(goods);
        //查询图片分享
        //查询相关评论
        //查询相关属性
        List<GoodsAttribute> attributeList = goodsService.queryAttribute(goods);
        //查询product
        List<GoodsProduct> productList = goodsService.queryProductByGoods(goods);
        //查询商品信息
        List<Goods> goodsList = goodsService.queryGoodsList(null, goods);
        Goods goods1 = goodsList.get(0);
        return ResultUtil.genSuccessResult(VoUtil.genDetailVo(
                specificationList,
                issueList,
                collectCount,
                productList,
                attributeList,
                goods1
        ));
    }

    @RequestMapping("/goods/related")
    public TResultVo goodsRelated(PageUtil pageUtil, Goods goods){
        Goods goods1 = goodsService.queryGoodsCategory(goods);
        //查询所有改目录的数量
        Integer cateCount = goodsService.queryCountCate(goods1);
        //根据商品自身目录，查找到所有相同目录的商品
        List<Goods> goodsList = goodsService.queryGoodsList(pageUtil, goods1);
        //计算pages
        Integer pages = 1 + cateCount / pageUtil.getPage();

        return ResultUtil.genSuccessResult(VoUtil.genGoodsVo(cateCount, pages, pageUtil.getLimit(), pageUtil.getPage(), goodsList));
    }

    @RequestMapping("/goods/count")
    public TResultVo goodsCount(){
        //查询在售商品树数量
        Integer goodsCount = goodsService.queryCountGoods();

        return ResultUtil.genSuccessResult(goodsCount);
    }

    @RequestMapping("/brand/list")
    public TResultVo brandList(PageUtil pageUtil){
        //查询brand总数
        Integer brandCount = goodsService.queryBrandCount();
        //计算pages
        Integer pages = 1 + brandCount / pageUtil.getPage();
        //查询brand列表
        List<Brand> brandList = goodsService.queryBrandList(null);

        return ResultUtil.genSuccessResult(VoUtil.genListVo(
                brandCount,
                pages,
                pageUtil.getLimit(),
                pageUtil.getPage(),
                brandList
        ));
    }

    @RequestMapping("/brand/detail")
    public TResultVo brandDetail(Brand brand){
        //查询brand单个数据
        List<Brand> brandList = goodsService.queryBrandList(brand);
        if(brandList.size()==1){
            return ResultUtil.genSuccessResult(brandList.get(0));
        }
        return ResultUtil.genFailResult("查询的品牌不存在!");
    }

    @RequestMapping("/goods/category")
    public TResultVo goodsCategory(Integer id){
        //查询当前cate
        Category cur = goodsService.findCateById(id);
        Category parent = null;
        List<Category> children = null;

        if(cur.getPid()==0){
            parent = cur;
            children = goodsService.queryCateByPid(cur.getId());
        }else{
            parent = goodsService.findCateById(cur.getPid());
            children = goodsService.queryCateByPid(parent.getId());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("currentCategory", cur);
        data.put("parentCategory", parent);
        data.put("brotherCategory", children);

        return ResultUtil.genSuccessResult(data);
    }
}
