package com.xjtuse.mall.mapper.admin;

import com.xjtuse.mall.bean.system.*;
import com.xjtuse.mall.utils.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 失了秩
 * @date 2020/3/14 16:18
 * @description
 */
public interface SystemMapper {
    void storageCreate(@Param("storage") Storage storage);

    List<AdminRes> queryadminList(@Param("pageUtil") PageUtil pageUtil, @Param("username") String username);

    int queryAdminCount(@Param("username") String username);

    List<LabelValue> queryRoleOptions();

    void updateAdminById(@Param("admin") Admin admin);

    List<Role> queryAdminRoles(@Param("pageUtil") PageUtil pageUtil,@Param("role")  Role role);

    int selectRolesCount(@Param("role") Role role);

    void updateRoleById(@Param("role") Role role);

    void setRoleDeleted(@Param("role") Role role);

    String queryPasswordByUsername(@Param("username") String username);

    void insertAdmin(@Param("admin") Admin admin);

    void setAdminDeleted(@Param("admin") Admin admin);

    List<Storage> queryStorages(@Param("pageUtil") PageUtil pageUtil,@Param("storage")  Storage storage);

    int queryStoragesCount(@Param("storage") Storage storage);

    void updateStorage(@Param("storage") Storage storage);

    void setStorageDeleted(@Param("storage") Storage storage);

    void insertRole(@Param("role") Role role);
}
