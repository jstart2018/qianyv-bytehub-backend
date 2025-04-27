package com.qianyvbytehub.auth.infra.basic.service;


import com.qianyvbytehub.auth.infra.basic.entity.AuthUserRole;

/**
 * 用户角色关系表(AuthUserRole)表服务接口
 *
 * @author makejava
 * @since 2024-12-13 10:04:00
 */
public interface AuthUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUserRole queryById(Long id);


    /**
     * 新增数据
     *
     * @param authUserRole 实例对象
     * @return 实例对象
     */
    Integer insert(AuthUserRole authUserRole);

    /**
     * 修改数据
     *
     * @param authUserRole 实例对象
     * @return 实例对象
     */
    AuthUserRole update(AuthUserRole authUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
