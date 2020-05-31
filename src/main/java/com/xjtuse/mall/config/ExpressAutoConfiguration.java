package com.xjtuse.mall.config;

import com.xjtuse.mall.common.ExpressProperties;
import com.xjtuse.mall.service.wx.impl.ExpressService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ExpressProperties.class})
public class ExpressAutoConfiguration {
    private final ExpressProperties properties;

    public ExpressAutoConfiguration(ExpressProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ExpressService expressService() {
        ExpressService expressService = new ExpressService();
        expressService.setProperties(this.properties);
        return expressService;
    }
}