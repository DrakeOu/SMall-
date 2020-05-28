package com.xjtuse.mall.controller.admin;

import com.xjtuse.mall.bean.system.Admin;
import com.xjtuse.mall.result.ResultVo;
import com.xjtuse.mall.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AuthController {

    @RequestMapping("/auth/login")
    public ResultVo login(@RequestBody Admin admin) {
        String username = admin.getUsername();
        String password= admin.getPassword();
        if (username == null || password == null || "".equals(password) || "".equals(username)) {
            return ResultUtil.fail(1, "用户名或密码参数错误！");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            return ResultUtil.fail(1, "用户名或密码错误！");
        }
        subject = SecurityUtils.getSubject();
        return ResultUtil.genSuccessResult();
    }

    @RequestMapping("/auth/info")
    public Map info(){
        Map<String,Object> map1 = new HashMap<>();
        Map<String,Object> map2 = new HashMap<>();

        map2.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map2.put("name","admin123");
        List perms = new ArrayList();
        perms.add("*");
        List roles = new ArrayList();
        roles.add("超级管理员");
        map2.put("perms",perms);
        map2.put("roles",roles);
        map1.put("data",map2);
        map1.put("errmsg","成功");
        map1.put("errno",0);
        return map1;
    }

    @RequestMapping("/dashboard")
    public String dashboard() {
        return "{\"errno\":0,\"data\":{\"goodsTotal\":99999999999999999999,\"userTotal\":10000000000000,\"productTotal\":241,\"orderTotal\":15},\"errmsg\":\"成功\"}";
    }
}
