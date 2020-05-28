package com.xjtuse.mall.mapper.admin;

import com.xjtuse.mall.bean.mall.*;
import com.xjtuse.mall.bean.promotion.Topic;
import com.xjtuse.mall.bean.user.User;
import com.xjtuse.mall.utils.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.security.Key;
import java.util.List;

/**
 * @author 失了秩
 * @date 2020/3/13 9:16
 * @description
 */
public interface MallMapper {
    List<ParentCategory> queryParentCategory();
    List<Category> queryChildrenCategory(@Param("parentCategory") ParentCategory parentCategory);

    List<Brand> queryBrand(@Param("pageUtil") PageUtil pageUtil,@Param("brand")  Brand brand);
    int queryBrandCount(@Param("brand") Brand brand);

    List<Region> queryAllRegion();

    void updateBrandInfo(Brand brand);

    void deleteBrand(Brand brand);

    void createBrand(Brand brand);

    void createCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Category category);

    List<Order> queryOrder(@Param("pageUtil") PageUtil pageUtil, @Param("order") Order order, @Param("orderStatusArray") int[] orderStatusArray);
    int queryOrderCount(@Param("pageUtil") PageUtil pageUtil, @Param("order") Order order, @Param("orderStatusArray") int[] orderStatusArray);

    Order queryOrderById(Order order);

    List<OrderAndGoods> queryOAGByOrderId(Order order);

    User queryUserById(Order order);

    List<Issue> queryIssue(@Param("pageUtil") PageUtil pageUtil, @Param("issue") Issue issue);
    int queryIssueCount(@Param("pageUtil") PageUtil pageUtil, @Param("issue") Issue issue);

    void updateIssue(Issue issue);

    void deleteIssue(Issue issue);

    void createIssue(Issue issue);

    List<Keyword> queryKeyword(@Param("pageUtil") PageUtil pageUtil, @Param("keyword") Keyword keyword);
    int queryKeywordCount(@Param("pageUtil") PageUtil pageUtil, @Param("keyword") Keyword keyword);

    void updateKeyword(Keyword keyword);

    void deleteKeyword(Keyword keyword);

    void createKeyword(Keyword keyword);
}
