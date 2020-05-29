package com.xjtuse.mall.controller.wx;

import com.alibaba.druid.util.StringUtils;
import com.xjtuse.mall.annotation.LoginUser;
import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.goods.GoodsProduct;
import com.xjtuse.mall.bean.user.Cart;
import com.xjtuse.mall.bean.user.User;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxGoodsService;
import com.xjtuse.mall.service.wx.WxUserService;
import com.xjtuse.mall.token.TokenService;
import com.xjtuse.mall.utils.JsonUtil;
import com.xjtuse.mall.utils.ResultUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class WxCartController {

    private final Log logger = LogFactory.getLog(WxCartController.class);

    @Autowired
    TokenService tokenService;

    @Autowired
    WxUserService userService;

    @Autowired
    WxGoodsService goodsService;

    @RequestMapping("/cart/index")
    public TResultVo CartInfo(@LoginUser Integer userId){
        //没有登录，返回登录错误，前端跳转登录
        if(null == userId){
            return ResultUtil.unlogin();
        }
        //查询user相关全部cart数据
        List<Cart> list =  userService.queryCartById(userId);

        List<Cart> cartList = new ArrayList<>();
        //清除全部过期的数据
        for(Cart cart: list){
            Goods goods = goodsService.queryGoodsById(cart.getGoodsId());
            if(goods == null || !goodsService.isOnSale(goods)){
                userService.deleteCartById(cart);
                logger.debug("清除过期商品");
            }else {
                cartList.add(cart);
            }
        }
        Integer goodsCount = 0;
        BigDecimal goodsAmount = new BigDecimal(0.00);
        Integer checkedGoodsCount = 0;
        BigDecimal checkedGoodsAmount = new BigDecimal(0.00);
        for (Cart cart : cartList) {
            goodsCount += cart.getNumber();
            goodsAmount = goodsAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            if (cart.getChecked()) {
                checkedGoodsCount += cart.getNumber();
                checkedGoodsAmount = checkedGoodsAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            }
        }
        Map<String, Object> cartTotal = new HashMap<>();
        cartTotal.put("goodsCount", goodsCount);
        cartTotal.put("goodsAmount", goodsAmount);
        cartTotal.put("checkedGoodsCount", checkedGoodsCount);
        cartTotal.put("checkedGoodsAmount", checkedGoodsAmount);

        Map<String, Object> result = new HashMap<>();
        result.put("cartList", cartList);
        result.put("cartTotal", cartTotal);

        return ResultUtil.genSuccessResult(result);
    }

    @RequestMapping("/cart/goodscount")
    public TResultVo goodsCount(@LoginUser Integer userId){
        if(userId==null){
            return ResultUtil.genSuccessResult(0);
        }
        List<Cart> list =  userService.queryCartById(userId);
        Integer goodsCount = 0;
        for(Cart cart: list){
            goodsCount += cart.getNumber();
        }
        return ResultUtil.genSuccessResult(goodsCount);
    }

    @RequestMapping("/cart/add")
    public TResultVo cartAdd(@LoginUser Integer userId, @RequestBody Cart cart){
        if(null == userId){
            return ResultUtil.unlogin();
        }
        if(cart == null){
            return ResultUtil.genFailResult("参数错误", 401);
        }

        Integer goodsId = cart.getGoodsId();
        Integer productId = cart.getProductId();
        Integer number = cart.getNumber().intValue();
        //检查商品是否可以购买
        Goods goods = goodsService.queryGoodsById(goodsId);
        if(goods == null||!goodsService.isOnSale(goods)){
            return ResultUtil.genFailResult("商品已下架");
        }
        //检查商品是否已在购物车中
        //goods中某种库存对应的product才是真正可以购买的东西
        GoodsProduct product = goodsService.queryProductByPid(productId);
        Cart existCart = userService.queryExistCart(goodsId, productId, userId);
        if(existCart==null){
            //判断库存类型是否充足
            if(product==null||product.getNumber()<number){
                return ResultUtil.genFailResult("库存不足!");
            }
            //设置购物车参数
            cart.setId(null);
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName((goods.getName()));
            if(StringUtils.isEmpty(product.getUrl())){
                cart.setPicUrl(goods.getPicUrl());
            }
            else{
                cart.setPicUrl(product.getUrl());
            }
            cart.setPrice(product.getPrice());
            cart.setSpecifications(product.getSpecifications());
            cart.setUserId(userId);
            cart.setChecked(true);
            userService.addCart(cart);
        }else{
            int num = number + existCart.getNumber();
            if(num > product.getNumber()){
                return ResultUtil.genFailResult("库存不足!");
            }
            existCart.setNumber((short) num);
            if(userService.updateCartById(existCart)==0){
                return ResultUtil.genFailResult("添加购物车失败!");
            }
        }
        return goodsCount(userId);
    }

    @RequestMapping("/cart/checked")
    public TResultVo cartChecked(@LoginUser Integer userId, @RequestBody String body){
        if(userId == null){
            return ResultUtil.unlogin();
        }
        if(body == null){
            return ResultUtil.genFailResult("参数错误");
        }
        List<Integer> productIds = JsonUtil.parseIntegerList(body, "productIds");
        if (productIds == null) {
            return ResultUtil.genFailResult("参数错误");
        }

        Integer checkValue = JsonUtil.parseInteger(body, "isChecked");
        if (checkValue == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        Boolean isChecked = (checkValue == 1);

        userService.updateCheck(userId, productIds, isChecked);
        return CartInfo(userId);

    }

}
