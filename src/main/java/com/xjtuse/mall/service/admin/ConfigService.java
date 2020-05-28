package com.xjtuse.mall.service.admin;

import com.xjtuse.mall.bean.configs.Config;
import com.xjtuse.mall.bean.configs.ConfigKeys;
import com.xjtuse.mall.result.TResultVo;

public interface ConfigService {

    TResultVo queryConfig();

    TResultVo updateConfig(ConfigKeys configKeys);

}
