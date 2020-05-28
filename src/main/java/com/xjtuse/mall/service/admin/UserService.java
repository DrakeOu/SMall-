package com.xjtuse.mall.service.admin;

import com.xjtuse.mall.bean.user.*;
import com.xjtuse.mall.result.MapResultVo;
import com.xjtuse.mall.utils.PageUtil;

public interface UserService {
    MapResultVo queryAllUser(PageUtil pageUtil, User user);

    MapResultVo queryAddress(PageUtil pageUtil, Address address);

    MapResultVo queryCollect(PageUtil pageUtil, Collect collect);

    MapResultVo queryFootprint(PageUtil pageUtil, Footprint footprint);

    MapResultVo querySearchHistory(PageUtil pageUtil, SearchHistory searchHistory);

    MapResultVo queryFeedback(PageUtil pageUtil, Feedback feedback);
}
