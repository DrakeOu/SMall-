package com.xjtuse.mall.config;

import com.xjtuse.mall.annotation.LoginUserHandlerMethodArgumentResolver;
import com.xjtuse.mall.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class WxWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    TokenService tokenService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginUserHandlerMethodArgumentResolver(tokenService));
    }
}
