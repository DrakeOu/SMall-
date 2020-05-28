package com.xjtuse.mall.VO;

import com.xjtuse.mall.bean.mall.Category;

import java.util.List;

public class CategoryVo {

    private List<Category> categoryList;
    private List<Category> currentSubCategory;
    private Category currentCategory;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getCurrentSubCategory() {
        return currentSubCategory;
    }

    public void setCurrentSubCategory(List<Category> currentSubCategory) {
        this.currentSubCategory = currentSubCategory;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }
}
