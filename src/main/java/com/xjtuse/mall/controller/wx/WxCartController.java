package com.xjtuse.mall.controller.wx;

import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.user.Cart;
import com.xjtuse.mall.bean.user.User;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxGoodsService;
import com.xjtuse.mall.service.wx.WxUserService;
import com.xjtuse.mall.token.TokenService;
import com.xjtuse.mall.utils.ResultUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public TResultVo CartInfo(HttpServletRequest request){
        String token = request.getHeader("X-Litemall-Token");
        boolean checkToken = tokenService.checkToken(token);
        //没有登录，返回登录错误，前端跳转登录
        if(false == checkToken){
            return ResultUtil.genFailResult("请登录", 501);
        }
//        return ResultUtil.genSuccessResult(checkToken);
        Integer userId = tokenService.getUserId(token);
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
    public TResultVo goodsCount(HttpServletRequest request){
        String token = request.getHeader("X-Litemall-Token");
        if(token==null){
            return ResultUtil.genSuccessResult(0);
        }
        Integer userId = tokenService.getUserId(token);
        List<Cart> list =  userService.queryCartById(userId);
        Integer goodsCount = 0;
        for(Cart cart: list){
            goodsCount += cart.getNumber();
        }
        return ResultUtil.genSuccessResult(goodsCount);
    }

}
