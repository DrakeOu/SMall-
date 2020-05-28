package com.xjtuse.mall.controller.admin;

import com.xjtuse.mall.bean.configs.ConfigKeys;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.admin.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ConfigController {

    @Autowired
    ConfigService service;

    @RequestMapping(value = "/config/**", method = RequestMethod.GET)
    public TResultVo queryConfig(){
        return service.queryConfig();
    }

    @RequestMapping(value = "/config/**", method = RequestMethod.POST)
    public TResultVo updateConfig(@RequestBody ConfigKeys configKeys){
        return service.updateConfig(configKeys);
    }

}
