package com.xjtuse.mall.utils;

/**
 * @author 失了秩
 * @date 2020/3/12 9:44
 * @description
 */
public class PageUtil {
    private Integer page;
    private Integer limit;
    private Integer start;
    private String sort;
    private String order;

    @Override
    public String toString() {
        return "PageUtil{" +
                "page=" + page +
                ", limit=" + limit +
                ", start=" + start +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                '}';
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void initStart() {
        this.setStart((this.getPage() -1) * this.getLimit());
    }

    public static PageUtil initPageUtil() {
        PageUtil pageUtil = new PageUtil();
        pageUtil.setStart(0);
        pageUtil.setLimit(20);
        pageUtil.setPage(1);
        pageUtil.setSort("id");
        pageUtil.setOrder("desc");
        return pageUtil;
    }
}
