package com.xjtuse.mall.controller.wx;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.mall.Brand;
import com.xjtuse.mall.bean.mall.Category;
import com.xjtuse.mall.bean.promotion.Ad;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.promotion.Groupon;
import com.xjtuse.mall.bean.promotion.Topic;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxGoodsService;
import com.xjtuse.mall.service.wx.WxOrderService;
import com.xjtuse.mall.service.wx.WxOtherService;
import com.xjtuse.mall.service.wx.WxUserService;
import com.xjtuse.mall.utils.ResultUtil;
import com.xjtuse.mall.utils.VoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wx")
public class WxIndexController {

    @Autowired
    WxUserService userService;

    @Autowired
    WxGoodsService goodsService;

    @Autowired
    WxOrderService orderService;

    @Autowired
    WxOtherService otherService;


    @RequestMapping("/home/index")
    public TResultVo homeIndex(){
        //前端一个服务依赖多个数据，所以controller一进来的目的就要去查找数据在哪个服务中（哪个库对应的服务）
        //查询优惠券
        List<Coupon> couponList = userService.queryAllCoupon();
        //查询一级目录
        List<Category> categoryList = goodsService.queryPriCate();
        //查询团购信息
        List<Groupon> grouponList = orderService.queryTwoGroupon();
        //查询广告，前端banner
        List<Ad> adList = otherService.queryAllAd();
        //查询4个品牌商
        List<Brand> brandList = goodsService.queryCountBrand(4);
        //查询热门商品 6个
        List<Goods> goodsList = goodsService.queryCountHotest(6);
        //查询话题列表
        List<Topic> topicList = otherService.queryCountTopic(4);
        //查询最新的商品
        List<Goods> newGoods = goodsService.queryCountNewGoods(6);

        return ResultUtil.genSuccessResult(VoUtil.genIndexVo(
                couponList,
                categoryList,
                grouponList,
                adList,
                brandList,
                goodsList,
                topicList,
                newGoods));
    }

}
