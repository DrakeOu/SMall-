package com.xjtuse.mall.service.wx;

import com.xjtuse.mall.bean.user.User;
import com.xjtuse.mall.result.TResultVo;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    TResultVo AuthLogin(User user);

    void logout(User user, HttpServletRequest request);
}
