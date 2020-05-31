package com.xjtuse.mall.controller.wx;


import com.xjtuse.mall.bean.mall.Issue;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.wx.WxOtherService;
import com.xjtuse.mall.utils.PageUtil;
import com.xjtuse.mall.utils.ResultUtil;
import com.xjtuse.mall.utils.VoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wx")
public class WxIssueController {

    @Autowired
    WxOtherService otherService;

    @RequestMapping("/issue/list")
    public TResultVo issueList(String question, PageUtil pageUtil){

        //查询全部issue
        List<Issue> issueList = otherService.queryAllIssue();
//        //计算count pages
//        Integer count = issueList.size();
//        Integer pages = 1 + count / pageUtil.getLimit();
//        //查询issue List
//        List<Issue> readIssue = otherService.queryIssueList(question, pageUtil);
        return ResultUtil.genSuccessResult(VoUtil.genListVo(
                issueList.size(),
                1,
                issueList.size(),
                1,
                issueList
        ));
    }
}
