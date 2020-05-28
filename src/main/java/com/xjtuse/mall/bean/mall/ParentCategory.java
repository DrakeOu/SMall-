package com.xjtuse.mall.bean.mall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 失了秩
 * @date 2020/3/13 9:25
 * @description
 */
public class ParentCategory extends Category {
    List<Category> children;

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
}
