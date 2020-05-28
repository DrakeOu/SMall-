package com.xjtuse.mall.controller.wx;

import com.xjtuse.mall.bean.user.User;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.AuthService;
import com.xjtuse.mall.token.TokenService;
import com.xjtuse.mall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wx")
public class WxAuthController {

    @Autowired
    AuthService authService;


    @RequestMapping("/auth/login")
    public TResultVo AuthLogin(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();

        if(username==null || "".equals(username) || password==null || "".equals(password)){
            return ResultUtil.genFailResult("用户名或密码错误", 601);
        }

        return authService.AuthLogin(user);
    }

    @RequestMapping("/auth/logout")
    public TResultVo AuthLogout(@RequestBody User user, HttpServletRequest request){
        try{
            authService.logout(user, request);
        } catch (Exception e){
            return ResultUtil.genFailResult(e.getMessage());
        }
        return ResultUtil.genSuccessResult();
    }

    @RequestMapping("/auth/info")
    public TResultVo AuthInfo(){
        return null;
    }

    @RequestMapping("/auth/profile")
    public TResultVo AuthProfile(){
        return null;
    }

}
