package com.xjtuse.mall.service.admin.impl;

import com.xjtuse.mall.bean.mall.*;
import com.xjtuse.mall.bean.user.User;
import com.xjtuse.mall.common.Constants;
import com.xjtuse.mall.mapper.admin.MallMapper;
import com.xjtuse.mall.result.*;
import com.xjtuse.mall.service.admin.MallService;
import com.xjtuse.mall.utils.MD5Util;
import com.xjtuse.mall.utils.ResultUtil;
import com.xjtuse.mall.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 失了秩
 * @date 2020/3/13 9:15
 * @description
 */
@Service
public class MallServiceImpl implements MallService {

    @Autowired
    MallMapper mapper;

    @Override
    public ListResultVo queryCategory() {
        List<ParentCategory> parentcategories = mapper.queryParentCategory();
        for (ParentCategory parentcategory : parentcategories) {
            List<Category> childrenCateGories = mapper.queryChildrenCategory(parentcategory);
            parentcategory.setChildren(childrenCateGories);
        }
        return ResultUtil.categoryOk(parentcategories);
    }

    @Override
    public ListResultVo queryCategoryL1() {
        List<ParentCategory> l1Categories = mapper.queryParentCategory();
        return ResultUtil.categoryOk(l1Categories);
    }

    @Override
    public MapResultVo queryBrand(PageUtil pageUtil, Brand brand) {
        if (pageUtil.getLimit() != null) {
            pageUtil.initStart();
        }


        List<Brand> data = mapper.queryBrand(pageUtil, brand);
        int count = mapper.queryBrandCount(brand);
        return ResultUtil.ok(data, count);
    }

    @Override
    public TResultVo queryAllRegion() {
        return ResultUtil.genSuccessResult(mapper.queryAllRegion());
    }

    @Override
    public TResultVo updateBrandInfo(Brand brand) {
        //更新操作日期
        brand.setUpdateTime(new Date());
        mapper.updateBrandInfo(brand);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo deleteBrand(Brand brand) {
        brand.setDeleted(true);
        brand.setUpdateTime(new Date());
        mapper.deleteBrand(brand);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo uploadBrandImg(MultipartFile multipartFile) {
        //写入文件到/resources/static中
        String fileName = null;
        try {

            String[] split = multipartFile.getOriginalFilename().split("\\.");
            fileName = MD5Util.MD5Encode(split[0], "UTF-8") + "." + split[1];
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(Constants.ROOT_PATH,
                    Constants.RELATIVE_PATH,
                    fileName);
            Files.write(path, bytes);
            //返回url数据
            HashMap<String, String> map = new HashMap<>();
            map.put("url", Constants.IMG_URL + fileName);
            return ResultUtil.genSuccessResult(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TResultVo createBrand(Brand brand) {
        brand.setUpdateTime(new Date());
        brand.setAddTime(new Date());
        mapper.createBrand(brand);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo createCategory(Category category) {
        mapper.createCategory(category);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo updateCategory(Category category) {
        category.setUpdateTime(new Date());
        mapper.updateCategory(category);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo deleteCategory(Category category) {
        category.setDeleted(true);
        category.setUpdateTime(new Date());
        mapper.deleteCategory(category);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public MapResultVo queryOrder(PageUtil pageUtil, Order order, int[] orderStatusArray) {
        if (pageUtil.getLimit() != null) {
            pageUtil.initStart();
        }
        order.setDeleted(false);
        Integer count = queryOrderCount(pageUtil, order, orderStatusArray);
        List<Order> orders = mapper.queryOrder(pageUtil, order, orderStatusArray);
        return ResultUtil.ok(orders, count);
    }

    @Override
    public int queryOrderCount(PageUtil pageUtil, Order order, int[] orderStatusArray) {
        return mapper.queryOrderCount(pageUtil, order, orderStatusArray);
    }

    @Override
    public TResultVo queryOrderById(Order order) {
        Map<String, Object> map = new HashMap<>();
        Order realOrder = mapper.queryOrderById(order);
        List<OrderAndGoods> orderAndGoods = mapper.queryOAGByOrderId(realOrder);
        User user = mapper.queryUserById(realOrder);
        map.put("user", user);
        map.put("order", realOrder);
        map.put("orderGoods", orderAndGoods);
        return ResultUtil.genSuccessResult(map);
    }

    @Override
    public MapResultVo queryIssue(PageUtil pageUtil, Issue issue) {
        if (pageUtil.getLimit() != null) {
            pageUtil.initStart();
        }
        List<Issue> issues = mapper.queryIssue(pageUtil, issue);
        int count = mapper.queryIssueCount(pageUtil, issue);
        return ResultUtil.ok(issues, count);
    }

    @Override
    public TResultVo updateIssue(Issue issue) {
        issue.setUpdateTime(new Date());
        mapper.updateIssue(issue);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo deleteIssue(Issue issue) {
        issue.setDeleted(true);
        issue.setUpdateTime(new Date());
        mapper.deleteIssue(issue);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo createIssue(Issue issue) {
        issue.setAddTime(new Date());
        issue.setUpdateTime(new Date());
        issue.setDeleted(false);
        mapper.createIssue(issue);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public MapResultVo queryKeyword(PageUtil pageUtil, Keyword keyword) {
        if (pageUtil.getLimit() != null) {
            pageUtil.initStart();
        }
        List<Keyword> keywords = mapper.queryKeyword(pageUtil, keyword);
        int count = mapper.queryKeywordCount(pageUtil, keyword);
        return ResultUtil.ok(keywords, count);
    }

    @Override
    public TResultVo updateKeyword(Keyword keyword) {
        keyword.setUpdateTime(new Date());
        mapper.updateKeyword(keyword);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo deleteKeyword(Keyword keyword) {
        keyword.setUpdateTime(new Date());
        keyword.setDeleted(true);
        mapper.deleteKeyword(keyword);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo createKeyword(Keyword keyword) {
        Date date = new Date();
        keyword.setAddTime(date);
        keyword.setUpdateTime(date);
        keyword.setDeleted(false);
        mapper.createKeyword(keyword);
        return ResultUtil.genSuccessResult();
    }


}
