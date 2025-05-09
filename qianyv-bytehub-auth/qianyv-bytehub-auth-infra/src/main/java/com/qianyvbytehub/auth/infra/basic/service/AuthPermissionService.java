package com.qianyvbytehub.auth.infra.basic.service;


import com.qianyvbytehub.auth.infra.basic.entity.AuthPermission;

import java.util.List;

/**
 * 权限表(AuthPermission)表服务接口
 *
 * @author makejava
 * @since 2024-12-13 10:04:29
 */
public interface AuthPermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthPermission queryById(Long id);

    /**
     * 根据id批量查
     * @param permissionIdList
     * @return
     */
    List<AuthPermission> queryByIdList(List<Long> permissionIdList);



    /**
     * 新增数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    Integer insert(AuthPermission authPermission);

    /**
     * 修改数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    Integer update(AuthPermission authPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
