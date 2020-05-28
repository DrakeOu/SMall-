package com.xjtuse.mall.service.admin.impl;

import com.xjtuse.mall.bean.configs.Config;
import com.xjtuse.mall.bean.configs.ConfigKeys;
import com.xjtuse.mall.mapper.admin.ConfigMapper;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.admin.ConfigService;
import com.xjtuse.mall.utils.BeanReflectUtil;
import com.xjtuse.mall.utils.ResultUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigServiceImpl implements ConfigService, ApplicationContextAware {

    @Autowired
    ConfigMapper mapper;

    private ApplicationContext applicationContext;

    @Override
    public TResultVo queryConfig() {
        List<Config> configs = mapper.queryValue();
        Map<String, String> map = new HashMap<>();
        for (Config config :
                configs) {
            map.put(config.getKeyName(), config.getKeyValue());
        }
        ConfigKeys configKeys = (ConfigKeys) applicationContext.getBean("configKeys");
        BeanReflectUtil.setFieldValue(configKeys, map);
        return ResultUtil.genSuccessResult(configKeys);
    }

    @Override
    public TResultVo updateConfig(ConfigKeys configKeys) {
        Map<String, Object> filedMap = BeanReflectUtil.getFiledMap(configKeys);
        for(Map.Entry<String, Object> entry :
                filedMap.entrySet()) {
            Config config = (Config) applicationContext.getBean("config");
            config.setKeyName(entry.getKey());
            config.setKeyValue(entry.getValue().toString());
            config.setUpdateTime(new Date());
            mapper.updateValue(config);
        }
        return ResultUtil.genSuccessResult();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
