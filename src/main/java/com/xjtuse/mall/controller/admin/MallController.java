package com.xjtuse.mall.controller.admin;

import com.xjtuse.mall.bean.mall.*;
import com.xjtuse.mall.result.*;
import com.xjtuse.mall.service.admin.MallService;
import com.xjtuse.mall.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 失了秩
 * @date 2020/3/13 9:11
 * @description
 */
@RestController
@RequestMapping("/admin")
public class MallController {

    @Autowired
    MallService mallService;

    //商品类目
    @RequestMapping("/category/list")
    public ResultVo categoryList() {
        return mallService.queryCategory();
    }

    //L1商品类目
    @RequestMapping("/category/l1")
    public ResultVo categoryL1() {
        return mallService.queryCategoryL1();
    }

    //供应商查询
    @RequestMapping("/brand/list")
    public ResultVo brandList(PageUtil pageUtil, Brand brand) {
        return mallService.queryBrand(pageUtil, brand);
    }

    //行政地区查询
    @RequestMapping("/region/list")
    public TResultVo queryAllRegion(){
        return mallService.queryAllRegion();
    }

    //更新品牌商信息
    @RequestMapping("/brand/update")
    public TResultVo updateBrandInfo(@RequestBody Brand brand){
        return mallService.updateBrandInfo(brand);
    }

    //删除品牌商
    @RequestMapping("/brand/delete")
    public TResultVo deleteBrand(@RequestBody Brand brand){
        return mallService.deleteBrand(brand);
    }

    //上传品牌商图片
//    @RequestMapping("/storage/create")
//    public TResultVo uploadBrand(@RequestParam("file") MultipartFile multipartFile){
//        return mallService.uploadBrandImg(multipartFile);
//    }

    //上传新的品牌商
    @RequestMapping("/brand/create")
    public TResultVo createBrand(@RequestBody Brand brand){
        return mallService.createBrand(brand);
    }

    //上传类目
    @RequestMapping("/category/create")
    public TResultVo createCategory(@RequestBody Category category){ return mallService.createCategory(category); }

    //更新类目
    @RequestMapping("/category/update")
    public TResultVo updateCategory(@RequestBody Category category){ return mallService.updateCategory(category); }

    //删除类目
    @RequestMapping("/category/delete")
    public TResultVo deleteCategory(@RequestBody Category category){ return mallService.deleteCategory(category); }

    //查询订单
    @RequestMapping("/order/list")
    public MapResultVo queryOrder(PageUtil pageUtil, Order order, int[] orderStatusArray){
        return mallService.queryOrder(pageUtil, order, orderStatusArray);
    }

    //订单详情
    @RequestMapping("/order/detail")
    public TResultVo queryOrderDetailById(Order order){
        return mallService.queryOrderById(order);
    }

    //查询问题
    @RequestMapping("/issue/list")
    public MapResultVo queryIssue(PageUtil pageUtil, Issue issue){
        return mallService.queryIssue(pageUtil, issue);
    }

    //更新问题
    @RequestMapping("/issue/update")
    public TResultVo updateIssue(@RequestBody Issue issue){
        return mallService.updateIssue(issue);
    }

    //删除问题
    @RequestMapping("/issue/delete")
    public TResultVo deleteIssue(@RequestBody Issue issue){
        return mallService.deleteIssue(issue);
    }

    //添加问题
    @RequestMapping("/issue/create")
    public TResultVo createIssue(@RequestBody Issue issue){
        return mallService.createIssue(issue);
    }

    //查询关键字
    @RequestMapping("/keyword/list")
    public MapResultVo queryKeyword(PageUtil pageUtil, Keyword keyword){
        return  mallService.queryKeyword(pageUtil, keyword);
    }

    //更新关键字
    //注意使用isXXX的变量名 生成的getter setter会被简化成XXX
    @RequestMapping("/keyword/update")
    public TResultVo updateKeyword(@RequestBody Keyword keyword){
        return mallService.updateKeyword(keyword);
    }

    //删除关键字
    @RequestMapping("/keyword/delete")
    public TResultVo deleteKeyword(@RequestBody Keyword keyword){
        return mallService.deleteKeyword(keyword);
    }

    //添加关键字
    @RequestMapping("/keyword/create")
    public TResultVo createKeyword(@RequestBody Keyword keyword){
        return mallService.createKeyword(keyword);
    }
}
