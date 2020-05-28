package com.xjtuse.mall.bean.mall;

public class Region {

    private Integer id;
    private Integer pid;
    private String name;
    private Integer type;
    private Integer code;

    public Region() {
    }

    public Region(Integer id, Integer pid, String name, Integer type, Integer code) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.type = type;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
