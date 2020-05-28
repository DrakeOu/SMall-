package com.xjtuse.mall.config;


import com.xjtuse.mall.shiro.CustomRealm;
import com.xjtuse.mall.shiro.MyAuthenticationFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.HashMap;
import java.util.Properties;

/**
 * @author 失了秩
 * @date 2020/3/17 15:51
 * @description
 */
@Configuration
class ShiroConfig {
    /*注册securityManager*/
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //注册realmreaml
        defaultWebSecurityManager.setRealm(new CustomRealm());
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/auth/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/unAuthor");

        HashMap<String, String> filterMap = new HashMap<>();
        filterMap.put("/auth/unAuthor", "anon");
        filterMap.put("/auth/login", "anon");
        filterMap.put("/auth/logout", "logout");
//        filterMap.put("/user/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //巨坑Cors跨域问题
        shiroFilterFactoryBean.getFilters().put("authc",new MyAuthenticationFilter());
        return shiroFilterFactoryBean;
    }

    //Shioro切面支持模块
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
}
