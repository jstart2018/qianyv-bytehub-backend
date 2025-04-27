package com.qianyvbytehub.auth.infra.basic.service;


import com.qianyvbytehub.auth.infra.basic.entity.AuthRole;

/**
 * 角色表(AuthRole)表服务接口
 *
 * @author makejava
 * @since 2024-12-13 10:03:24
 */
public interface AuthRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRole queryById(Long id);

    /**
     * 条件查询
     */
    AuthRole queryByCondition(AuthRole authRole);


    /**
     * 新增数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    Integer insert(AuthRole authRole);

    /**
     * 修改数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    Integer update(AuthRole authRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
