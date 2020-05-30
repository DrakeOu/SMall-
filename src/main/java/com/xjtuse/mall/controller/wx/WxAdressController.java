package com.xjtuse.mall.controller.wx;

import com.xjtuse.mall.annotation.LoginUser;
import com.xjtuse.mall.bean.user.Address;
import com.xjtuse.mall.bean.user.LitemallAddress;
import com.xjtuse.mall.service.wx.WxUserService;
import com.xjtuse.mall.utils.RegexUtil;
import com.xjtuse.mall.utils.ResultUtil;
import com.xjtuse.mall.utils.VoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/wx")
public class WxAdressController {


    @Autowired
    WxUserService userService;

    /**
     * 用户收货地址列表
     * @return 收货地址列表
     */
    @GetMapping("/address/list")
    public Object list(@LoginUser Integer userId) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }
        List<LitemallAddress> addressList = userService.queryAddressListByUid(userId);
        return ResultUtil.genSuccessResult(VoUtil.genListVo(
                addressList.size(),
                2,
                addressList.size(),
                1,
                addressList
        ));
    }

    /**
     * 收货地址详情
     * @return 收货地址详情
     */
    @GetMapping("/address/detail")
    public Object detail(@LoginUser Integer userId, @NotNull Integer id) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }

        LitemallAddress address = userService.queryAddress(userId, id);
        if (address == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        return ResultUtil.genSuccessResult(address);
    }

    private Object validate(LitemallAddress address) {
        String name = address.getName();
        if (StringUtils.isEmpty(name)) {
            return ResultUtil.genFailResult("参数错误");
        }

        // 测试收货手机号码是否正确
        String mobile = address.getTel();
        if (StringUtils.isEmpty(mobile)) {
            return ResultUtil.genFailResult("参数错误");
        }

        if (!RegexUtil.isMobileExact(mobile)) {
            return ResultUtil.genFailResult("参数错误");
        }

        String province = address.getProvince();
        if (StringUtils.isEmpty(province)) {
            return ResultUtil.genFailResult("参数错误");
        }

        String city = address.getCity();
        if (StringUtils.isEmpty(city)) {
            return ResultUtil.genFailResult("参数错误");
        }

        String county = address.getCounty();
        if (StringUtils.isEmpty(county)) {
            return ResultUtil.genFailResult("参数错误");
        }


        String areaCode = address.getAreaCode();
        if (StringUtils.isEmpty(areaCode)) {
            return ResultUtil.genFailResult("参数错误");
        }

        String detailedAddress = address.getAddressDetail();
        if (StringUtils.isEmpty(detailedAddress)) {
            return ResultUtil.genFailResult("参数错误");
        }

        Boolean isDefault = address.getIsDefault();
        if (isDefault == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        return null;
    }

    /**
     * 添加或更新收货地址
     * @return 添加或更新操作结果
     */
    @PostMapping("/address/save")
    public Object save(@LoginUser Integer userId, @RequestBody LitemallAddress address) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }
        Object error = validate(address);
        if (error != null) {
            return error;
        }

        if (address.getId() == null || address.getId().equals(0)) {
            if (address.getIsDefault()) {
                // 重置其他收货地址的默认选项
                userService.resetDefaultAddress(userId);
            }
            address.setId(null);
            address.setUserId(userId);
            userService.addAddress(address);
        } else {
            LitemallAddress litemallAddress = userService.queryAddress(userId, address.getId());
            if (litemallAddress == null) {
                return ResultUtil.genFailResult("参数错误");
            }

            if (address.getIsDefault()) {
                // 重置其他收货地址的默认选项
                userService.resetDefaultAddress(userId);
            }

            address.setUserId(userId);
            userService.updateAddress(address);
        }
        return ResultUtil.genSuccessResult(address.getId());
    }

    /**
     * 删除收货地址
     * @return 删除操作结果
     */
    @PostMapping("/address/delete")
    public Object delete(@LoginUser Integer userId, @RequestBody LitemallAddress address) {
        if (userId == null) {
            return ResultUtil.unlogin();
        }
        Integer id = address.getId();
        if (id == null) {
            return ResultUtil.genFailResult("参数错误");
        }
        LitemallAddress litemallAddress = userService.queryAddress(userId, id);
        if (litemallAddress == null) {
            return ResultUtil.genFailResult("参数错误");
        }

        userService.deleteAddressById(id);
        return ResultUtil.genSuccessResult();
    }

}
