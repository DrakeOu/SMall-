package com.xjtuse.mall.controller.wx;

import com.xjtuse.mall.bean.mall.Category;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxGoodsService;
import com.xjtuse.mall.utils.ResultUtil;
import com.xjtuse.mall.utils.VoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wx")
public class WxCategoryController {

    @Autowired
    WxGoodsService goodsService;

    @RequestMapping("/catalog/index")
    public TResultVo catalogIndex(Integer id){
        //查询l1级目录
        List<Category> categoryList = goodsService.queryL1();
        //获取当前l1cata
        Category currentCategory = null;
        //刚打开这个页面，默认显示第一个，之后打开什么cate显示什么
        if(id != null){
            currentCategory = goodsService.findCateById(id);
        }else{
            currentCategory = categoryList.get(0);
        }
        //查询该目录下的子目录
        List<Category> subCategoryList = goodsService.queryCateByPid(currentCategory.getId());

        return ResultUtil.genSuccessResult(VoUtil.genCategoryVo(
                categoryList,
                currentCategory,
                subCategoryList
        ));
    }

    @RequestMapping("/catalog/current")
    public TResultVo catalogCurrent(Category category){
        if(category==null){
            return ResultUtil.genFailResult("查询分类异常!");
        }
        //根据当前分类查询子分类
        List<Category> subCategoryList = goodsService.queryCateByPid(category.getId());

        return ResultUtil.genSuccessResult(VoUtil.genCategoryVo(
                null,
                category,
                subCategoryList
        ));
    }


}
