package com.xjtuse.mall.service.wx.impl;

import com.xjtuse.mall.bean.promotion.Groupon;
import com.xjtuse.mall.mapper.wx.WxOrderMapper;
import com.xjtuse.mall.service.wx.WxOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    WxOrderMapper orderMapper;

    @Override
    public List<Groupon> queryTwoGroupon() {
        return orderMapper.queryTwoGroupon();
    }
}
