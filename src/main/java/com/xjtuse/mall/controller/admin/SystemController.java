package com.xjtuse.mall.controller.admin;

import com.xjtuse.mall.bean.system.Admin;
import com.xjtuse.mall.bean.system.Role;
import com.xjtuse.mall.bean.system.Storage;
import com.xjtuse.mall.result.ResultVo;
import com.xjtuse.mall.service.admin.SystemService;
import com.xjtuse.mall.utils.PageUtil;
import com.xjtuse.mall.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author 失了秩
 * @date 2020/3/14 14:27
 * @description
 */
@RestController
@RequestMapping("/admin")
public class SystemController {

    @Autowired
    SystemService service;

    @RequestMapping("/storage/create")
    public ResultVo storageCreate(MultipartFile file) {
        return service.storageCreate(file);
    }

    @RequestMapping("/storage/fetch/{path}")
    public void storageFetch(@PathVariable("path") String path, HttpServletResponse response) {

        try( ServletOutputStream outputStream = response.getOutputStream();
             FileInputStream inputStream = new FileInputStream(new File("small/src/main/resources/static/" + path));
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
//            int available = bufferedInputStream.available();
//            System.out.println(available);
//            byte[] bytes = new byte[available];
//            bufferedInputStream.read(bytes);
//            outputStream.write(bytes);


            byte[] bytes = new byte[1024];
            int lenth;
            while ((lenth = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes,0,lenth);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/storage/list")
    public ResultVo StorageList(PageUtil pageUtil, Storage storage) {
        return service.storageList(pageUtil, storage);
    }

    @RequestMapping("/admin/list")
    public ResultVo adminList(PageUtil pageUtil, String username) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        boolean authenticated = subject.isAuthenticated();
        boolean remembered = subject.isRemembered();
        Object principal = subject.getPrincipal();
        return service.adminList(pageUtil, username);
    }
    @RequestMapping("/role/options")
    public ResultVo roleOptions() {
        return service.roleOptions();
    }

    @RequestMapping("/admin/update")
    public ResultVo adminUpdate(@RequestBody Admin admin) {
        return service.adminUpdate(admin);
    }

    @RequestMapping("/role/list")
    public ResultVo adminRole(PageUtil pageUtil, Role role) {
        return service.adminRole(pageUtil, role);
    }

    @RequestMapping("/role/update")
    public ResultVo roleUpdate(@RequestBody Role role) {
        return service.roleUpdate(role);
    }

    @RequestMapping("/role/delete")
    public ResultVo roleDelete(@RequestBody Role role) {
        return service.roleDelete(role);
    }

    @RequestMapping("/admin/delete")
    public ResultVo adminDelete(@RequestBody Admin admin) {
        if (admin.getId() == 1) {
            return ResultUtil.fail(100,"不能删除超级管理员");
        } else if(admin.getId() == null) {
            return ResultUtil.fail(100,"请刷新页面");
        }
        return service.adminDelete(admin);
    }

    @RequestMapping("/admin/create")
    public ResultVo adminCreate(@RequestBody Admin admin) {
        return service.adminInsert(admin);
    }

    @RequestMapping("/storage/update")
    public ResultVo storageUpdate(@RequestBody Storage storage) {
        if (storage.getKey() == null) {
            return ResultUtil.fail(100,"参数错误");
        }
        return service.storageUpdate(storage);
    }

    @RequestMapping("/storage/delete")
    public ResultVo storageDelete(@RequestBody Storage storage) {
        if (storage.getKey() == null) {
            return ResultUtil.fail(100,"参数错误");
        }
        return service.storageDelete(storage);
    }

    @RequestMapping("/role/create")
    public ResultVo roleCreate(@RequestBody Role role) {
        if (role.getName() == null) {
            return ResultUtil.fail(100,"参数错误");
        }
        return service.roleCreate(role);
    }

}
