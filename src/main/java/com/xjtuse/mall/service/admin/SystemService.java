package com.xjtuse.mall.service.admin;

import com.xjtuse.mall.bean.system.Admin;
import com.xjtuse.mall.bean.system.Role;
import com.xjtuse.mall.bean.system.Storage;
import com.xjtuse.mall.result.ResultVo;
import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.utils.PageUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 失了秩
 * @date 2020/3/14 16:14
 * @description
 */
public interface SystemService {
    ResultVo storageCreate(MultipartFile file);

    ResultVo adminList(PageUtil pageUtil, String username);

    ResultVo roleOptions();

    ResultVo adminUpdate(Admin admin);

    TResultVo adminRole(PageUtil pageUtil, Role role);

    ResultVo roleUpdate(Role role);

    ResultVo roleDelete(Role role);

    ResultVo adminInsert(Admin admin);

    ResultVo adminDelete(Admin admin);

    ResultVo storageList(PageUtil pageUtil, Storage storage);

    ResultVo storageUpdate(Storage storage);

    ResultVo storageDelete(Storage storage);

    ResultVo roleCreate(Role role);
}
