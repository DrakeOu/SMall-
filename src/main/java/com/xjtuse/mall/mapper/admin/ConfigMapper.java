package com.xjtuse.mall.mapper.admin;

import com.xjtuse.mall.bean.configs.Config;
import com.xjtuse.mall.result.TResultVo;

import java.util.List;

public interface ConfigMapper {

    List<Config> queryValue();

    void updateValue(Config config);

}
