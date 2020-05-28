package com.xjtuse.mall.mapper.admin;

import com.xjtuse.mall.bean.user.*;
import com.xjtuse.mall.utils.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> queryAllUser(@Param("pageUtil") PageUtil pageUtil, @Param("user") User user);
    Integer queryUserCount(@Param("user") User user);

    List<Address> queryAddress(@Param("pageUtil") PageUtil pageUtil,@Param("address") Address address);
    Integer queryAddressCount(@Param("address") Address address);

    List<Collect> queryCollect(@Param("pageUtil") PageUtil pageUtil,@Param("collect") Collect collect);
    Integer queryCollectCount(@Param("collect") Collect collect);

    List<Collect> queryFootprint(@Param("pageUtil") PageUtil pageUtil,@Param("footprint") Footprint footprint);
    Integer queryFootprintCount(@Param("footprint") Footprint footprint);

    List<Collect> querySearchHistory(@Param("pageUtil") PageUtil pageUtil,@Param("searchHistory") SearchHistory searchHistory);
    Integer querySearchHistoryCount(@Param("searchHistory") SearchHistory searchHistory);

    List<Collect> queryFeedback(@Param("pageUtil") PageUtil pageUtil,@Param("feedback") Feedback feedback);
    Integer queryFeedbackCount(@Param("feedback") Feedback feedback);
}
