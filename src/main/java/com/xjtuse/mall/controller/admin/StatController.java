package com.xjtuse.mall.controller.admin;

import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.admin.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class StatController {

    @Autowired
    StatService service;

    @RequestMapping("/stat/user")
    public TResultVo queryUserStat(){
        return service.queryUserStat();
    }

    @RequestMapping("/stat/order")
    public TResultVo queryOrderStat(){
        return service.queryOrderStat();
    }

    @RequestMapping("/stat/goods")
    public TResultVo queryGoodsStat(){
        return service.queryGoodsStat();
    }
}
