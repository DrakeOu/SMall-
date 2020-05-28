package com.xjtuse.mall.service.wx.impl;

import com.xjtuse.mall.token.TokenService;
import com.xjtuse.mall.utils.VoUtil;
import com.xjtuse.mall.bean.user.User;
import com.xjtuse.mall.mapper.wx.AuthMapper;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.AuthService;
import com.xjtuse.mall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthMapper authMapper;

    @Autowired
    TokenService tokenService;

    @Override
    public TResultVo AuthLogin(User user) {
        User realUser = authMapper.queryUser(user);
        if(realUser==null) {
            return ResultUtil.genFailResult("用户不存在!!!", 601);
        }if(!user.getPassword().equals(realUser.getPassword())){
            return ResultUtil.genFailResult("用户名密码错误!!!", 601);
        }
        //写入token
        String token = tokenService.putToken(user);
        return ResultUtil.genSuccessResult(VoUtil.genUserInfo(user, token));
    }

    @Override
    public void logout(User user, HttpServletRequest request) {
        String token = request.getHeader("X-Litemall-Token");
        //登出用户，直接清除redis中的token即可
        tokenService.dropToken(token);
    }
}
