package com.xjtuse.mall.controller.admin;

import com.xjtuse.mall.bean.user.*;
import com.xjtuse.mall.result.ResultVo;
import com.xjtuse.mall.service.admin.UserService;
import com.xjtuse.mall.utils.PageUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/user/list")
    public ResultVo userList(PageUtil pageUtil, User user) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return userService.queryAllUser(pageUtil, user);
    }

    @RequestMapping("/address/list")
    public ResultVo addressList(PageUtil pageUtil, Address address) {
        return userService.queryAddress(pageUtil, address);
    }

    @RequestMapping("/collect/list")
    public ResultVo collectionList(PageUtil pageUtil, Collect collect) {
        return userService.queryCollect(pageUtil, collect);
    }

    @RequestMapping("/footprint/list")
    public ResultVo footprintList(PageUtil pageUtil, Footprint footprint) {
        return userService.queryFootprint(pageUtil, footprint);
    }

    @RequestMapping("/history/list")
    public ResultVo historyList(PageUtil pageUtil, SearchHistory searchHistory) {
        return userService.querySearchHistory(pageUtil, searchHistory);
    }

    @RequestMapping("/feedback/list")
    public ResultVo feedbackList(PageUtil pageUtil, Feedback feedback) {
        return userService.queryFeedback(pageUtil, feedback);
    }

}
