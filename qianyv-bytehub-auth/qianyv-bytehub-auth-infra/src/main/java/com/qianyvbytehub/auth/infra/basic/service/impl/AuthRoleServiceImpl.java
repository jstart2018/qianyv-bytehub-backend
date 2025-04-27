package com.qianyvbytehub.auth.infra.basic.service.impl;


import com.qianyvbytehub.auth.infra.basic.dao.AuthRoleDao;
import com.qianyvbytehub.auth.infra.basic.entity.AuthRole;
import com.qianyvbytehub.auth.infra.basic.service.AuthRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色表(AuthRole)表服务实现类
 *
 * @author makejava
 * @since 2024-12-13 10:03:24
 */
@Service("authRoleService")
public class AuthRoleServiceImpl implements AuthRoleService {
    @Resource
    private AuthRoleDao authRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthRole queryById(Long id) {
        return this.authRoleDao.queryById(id);
    }

    /**
     * 条件查询
     * @param authRole
     * @return
     */
    @Override
    public AuthRole queryByCondition(AuthRole authRole) {
        return this.authRoleDao.queryAllByCondition(authRole);
    }


    /**
     * 新增数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(AuthRole authRole) {
        return this.authRoleDao.insert(authRole);
    }

    /**
     * 修改数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(AuthRole authRole) {

        return this.authRoleDao.update(authRole);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authRoleDao.deleteById(id) > 0;
    }
}
