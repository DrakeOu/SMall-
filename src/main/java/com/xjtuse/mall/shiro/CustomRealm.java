package com.xjtuse.mall.shiro;

import com.xjtuse.mall.mapper.admin.SystemMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 失了秩
 * @date 2020/3/17 15:52
 * @description
 */
@Component
public class CustomRealm extends AuthenticatingRealm {

    @Autowired
    SystemMapper mapper;

    //    授权操作（鉴权）
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        return null;
//    }

    //    认证操作（登录）
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
//        String password =  mapper.queryPasswordByUsername(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, "admin123", "customRealm");
        return info;
    }
}
