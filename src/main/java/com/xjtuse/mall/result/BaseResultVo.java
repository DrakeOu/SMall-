package com.xjtuse.mall.result;

import com.xjtuse.mall.bean.system.Storage;

/**
 * @author 失了秩
 * @date 2020/3/14 17:28
 * @description
 */
public class BaseResultVo extends ResultVo {
    Storage data;

    public Storage getData() {
        return data;
    }

    public void setData(Storage data) {
        this.data = data;
    }
}
