package com.xjtuse.mall.service.admin.impl;

import com.xjtuse.mall.bean.system.*;
import com.xjtuse.mall.mapper.admin.SystemMapper;
import com.xjtuse.mall.result.BaseResultVo;
import com.xjtuse.mall.result.ResultVo;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.service.admin.SystemService;
import com.xjtuse.mall.utils.FileUploadUtil;
import com.xjtuse.mall.utils.PageUtil;
import com.xjtuse.mall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 失了秩
 * @date 2020/3/14 16:15
 * @description
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    SystemMapper mapper;

    @Override
    public BaseResultVo storageCreate(MultipartFile file) {
        Storage storage = FileUploadUtil.init(file);
        mapper.storageCreate(storage);
        BaseResultVo resultVo = ResultUtil.baseResultVoOk(storage);
        return resultVo;
    }

    @Override
    public ResultVo adminList(PageUtil pageUtil, String username) {
        pageUtil.initStart();
        List<AdminRes> list =  mapper.queryadminList(pageUtil, username);
        int total = mapper.queryAdminCount(username);
        Map map = new ConcurrentHashMap();
        map.put("items", list);
        map.put("total", total);
        return ResultUtil.genSuccessResult(map);
    }

    @Override
    public ResultVo roleOptions() {
        List<LabelValue> list = mapper.queryRoleOptions();
        return ResultUtil.genSuccessResult(list);
    }

    @Override
    public ResultVo adminUpdate(Admin admin) {
        mapper.updateAdminById(admin);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public TResultVo adminRole(PageUtil pageUtil, Role role) {
        pageUtil.initStart();
        List<Role> items = mapper.queryAdminRoles(pageUtil, role);
        int total = mapper.selectRolesCount(role);
        Map map = new HashMap();
        map.put("items", items);
        map.put("total", total);
        return ResultUtil.genSuccessResult(map);
    }

    @Override
    public ResultVo roleUpdate(Role role) {
        mapper.updateRoleById(role);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public ResultVo roleDelete(Role role) {
        mapper.setRoleDeleted(role);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public ResultVo adminInsert(Admin admin) {
        mapper.insertAdmin(admin);
        List<AdminRes> items = mapper.queryadminList(PageUtil.initPageUtil(), null);
        int total = mapper.queryAdminCount(null);
        Map map = new ConcurrentHashMap();
        map.put("items", items);
        map.put("total", total);
        return ResultUtil.genSuccessResult(map);
    }

    @Override
    public ResultVo adminDelete(Admin admin) {
        mapper.setAdminDeleted(admin);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public ResultVo storageList(PageUtil pageUtil, Storage storage) {
        pageUtil.initStart();
        List<Storage> items = mapper.queryStorages(pageUtil, storage);
        int total = mapper.queryStoragesCount(storage);
        Map map = new ConcurrentHashMap();
        map.put("items", items);
        map.put("total", total);
        return ResultUtil.genSuccessResult(map);
    }

    @Override
    public ResultVo storageUpdate(Storage storage) {
        mapper.updateStorage(storage);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public ResultVo storageDelete(Storage storage) {
        mapper.setStorageDeleted(storage);
        return ResultUtil.genSuccessResult();
    }

    @Override
    public ResultVo roleCreate(Role role) {
        mapper.insertRole(role);
        return ResultUtil.genSuccessResult(role);
    }
}
