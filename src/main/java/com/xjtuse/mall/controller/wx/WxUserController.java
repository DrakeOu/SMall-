package com.xjtuse.mall.controller.wx;


import com.xjtuse.mall.annotation.LoginUser;
import com.xjtuse.mall.bean.promotion.Coupon;
import com.xjtuse.mall.bean.promotion.CouponAndUser;
import com.xjtuse.mall.common.CouponConstant;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxOrderService;
import com.xjtuse.mall.service.wx.WxUserService;
import com.xjtuse.mall.utils.DateUtil;
import com.xjtuse.mall.utils.JsonUtil;
import com.xjtuse.mall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class WxUserController {

    @Autowired
    WxOrderService orderService;

    @Autowired
    WxUserService userService;

    @RequestMapping("/user/index")
    public TResultVo userIndex(@LoginUser Integer userId){
        if(userId == null){
            return ResultUtil.unlogin();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("order", orderService.orderInfo(userId));

        return ResultUtil.genSuccessResult(data);
    }


}
