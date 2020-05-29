package com.xjtuse.mall.controller.wx;

import com.xjtuse.mall.annotation.LoginUser;
import com.xjtuse.mall.bean.goods.Goods;
import com.xjtuse.mall.bean.user.Collect;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxGoodsService;
import com.xjtuse.mall.service.wx.WxUserService;
import com.xjtuse.mall.utils.JsonUtil;
import com.xjtuse.mall.utils.PageUtil;
import com.xjtuse.mall.utils.ResultUtil;
import com.xjtuse.mall.utils.VoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class WxCollectController {

    @Autowired
    WxUserService userService;

    @Autowired
    WxGoodsService goodsService;

    @RequestMapping("/collect/addordelete")
    public TResultVo addOrDelete(@LoginUser Integer userId, @RequestBody String body){
        if(userId == null){
            return ResultUtil.unlogin();
        }

        Byte type = JsonUtil.parseByte(body, "type");
        Integer valueId = JsonUtil.parseInteger(body, "valueId");
        //查询收藏情况
        Collect collect = userService.queryCollectByTypeAndValue(userId, type, valueId);
        if(collect!=null){
            //已经收藏则删除
            userService.deleteCollectById(collect);
        }else{
            //添加收藏
            Collect newCollect = new Collect();
            newCollect.setUserId(userId);
            newCollect.setType(type.intValue());
            newCollect.setValueId(valueId);

            userService.addCollect(newCollect);
        }
        return ResultUtil.genSuccessResult();
    }

    @RequestMapping("/collect/list")
    public TResultVo collectList(@LoginUser Integer userId, PageUtil pageUtil, Integer type){
        //查询收藏列表
        List<Collect> collectList = userService.queryCollectList(userId, pageUtil, type);
        Integer count = collectList.size();
        //计算pages
        Integer pages = 1 + count / pageUtil.getLimit();
        //根据收藏，查找对应的商品
        List<Object> collects = new ArrayList<>(collectList.size());
        for(Collect collect: collectList){
            Goods goods = goodsService.queryGoodsById(collect.getValueId());
            Map<String, Object> c = new HashMap<>();
            c.put("id", collect.getId());
            c.put("type", collect.getType());
            c.put("valueId", collect.getValueId());
            c.put("name", goods.getName());
            c.put("brief", goods.getBrief());
            c.put("picUrl", goods.getPicUrl());
            c.put("retailPrice", goods.getRetailPrice());

            collects.add(c);
        }

        return ResultUtil.genSuccessResult(VoUtil.genListVo(
                count,
                pages,
                pageUtil.getLimit(),
                pageUtil.getPage(),
                collects
        ));

    }
}
