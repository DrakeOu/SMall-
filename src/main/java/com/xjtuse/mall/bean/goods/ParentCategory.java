package com.xjtuse.mall.bean.goods;

import java.util.List;

/**
 * @author 失了秩
 * @date 2020/3/13 17:40
 * @description
 */
public class ParentCategory {
    private String label;
    private Integer value;
    List children;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }
}
