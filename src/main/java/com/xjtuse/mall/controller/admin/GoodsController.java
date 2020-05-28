package com.xjtuse.mall.controller.admin;

import com.xjtuse.mall.bean.goods.*;
import com.xjtuse.mall.result.ResultVo;
import com.xjtuse.mall.service.admin.GoodsService;
import com.xjtuse.mall.utils.PageUtil;
import com.xjtuse.mall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 失了秩
 * @date 2020/3/12 22:56
 * @description
 */
@RestController
@RequestMapping("/admin")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @RequestMapping("/goods/list")
    public ResultVo goodslist(PageUtil pageUtil, Goods goods) {
        return goodsService.queryGoods(pageUtil, goods);
    }

    @RequestMapping("/goods/detail")
    public ResultVo goodsDetail(Goods goods) {
        return goodsService.queryDetail(goods);
    }

    @RequestMapping("/goods/catAndBrand")
    public ResultVo catAndBrand(Goods goods) {
        return goodsService.queryCatAndBrand();
    }

    @RequestMapping("/goods/update")
    public ResultVo goodsUpdate(@RequestBody GoodsData data) {
        return goodsService.updateGoods(data);
    }

    @RequestMapping("/goods/delete")
    public ResultVo goodsDelete(@RequestBody Goods goods) {
        return goodsService.goodsDelete(goods);
    }

    @RequestMapping("/goods/create")
    public ResultVo goodsCreate(@RequestBody GoodsData data) {
        return goodsService.goodsCreate(data);
    }

    @RequestMapping("/comment/list")
    public ResultVo commentList(PageUtil pageUtil, Comment comment){
        return goodsService.commentList(pageUtil, comment);
    }

    @RequestMapping("/order/reply")
    public ResultVo orderReply() {
        return ResultUtil.genSuccessResult();
    }

    @RequestMapping("/comment/delete")
    public ResultVo commentDelet(@RequestBody Comment comment) {
        return goodsService.commentDelet(comment);
    }

}
