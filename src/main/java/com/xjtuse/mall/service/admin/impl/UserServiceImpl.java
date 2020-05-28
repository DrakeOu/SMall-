package com.xjtuse.mall.service.admin.impl;

import com.xjtuse.mall.bean.user.*;
import com.xjtuse.mall.mapper.admin.UserMapper;
import com.xjtuse.mall.result.MapResultVo;
import com.xjtuse.mall.service.admin.UserService;
import com.xjtuse.mall.utils.ResultUtil;
import com.xjtuse.mall.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 失了秩
 * @date 2020/3/11 19:19
 * @description
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;

    @Override
    public MapResultVo queryAllUser(PageUtil pageUtil, User user) {
        pageUtil.initStart();
        List<User> items = mapper.queryAllUser(pageUtil, user);
        Integer count = mapper.queryUserCount(user);
        return ResultUtil.ok(items, count);
    }

    @Override
    public MapResultVo queryAddress(PageUtil pageUtil, Address address) {
        pageUtil.initStart();
        List<Address> items = mapper.queryAddress(pageUtil, address);
        Integer count = mapper.queryAddressCount(address);
        return ResultUtil.ok(items, count);
    }

    @Override
    public MapResultVo queryCollect(PageUtil pageUtil, Collect collect) {
        pageUtil.initStart();
        List<Collect> items = mapper.queryCollect(pageUtil, collect);
        Integer count = mapper.queryCollectCount(collect);
        return ResultUtil.ok(items, count);
    }

    @Override
    public MapResultVo queryFootprint(PageUtil pageUtil, Footprint footprint) {
        pageUtil.initStart();
        List<Collect> items = mapper.queryFootprint(pageUtil, footprint);
        Integer count = mapper.queryFootprintCount(footprint);
        return ResultUtil.ok(items, count);
    }

    @Override
    public MapResultVo querySearchHistory(PageUtil pageUtil, SearchHistory searchHistory) {
        pageUtil.initStart();
        List<Collect> items = mapper.querySearchHistory(pageUtil, searchHistory);
        Integer count = mapper.querySearchHistoryCount(searchHistory);
        return ResultUtil.ok(items, count);
    }

    @Override
    public MapResultVo queryFeedback(PageUtil pageUtil, Feedback feedback) {
        pageUtil.initStart();
        List<Collect> items = mapper.queryFeedback(pageUtil, feedback);
        Integer count = mapper.queryFeedbackCount(feedback);
        return ResultUtil.ok(items, count);
    }


}
