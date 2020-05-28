package com.xjtuse.mall.controller.wx;

import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.token.TokenService;
import com.xjtuse.mall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wx")
public class WxCartController {

    @Autowired
    TokenService tokenService;

    @RequestMapping("/cart/index")
    public TResultVo CartInfo(HttpServletRequest request){
        String token = request.getHeader("X-Litemall-Token");
        boolean checkToken = tokenService.checkToken(token);
        return ResultUtil.genSuccessResult(checkToken);
    }

}
