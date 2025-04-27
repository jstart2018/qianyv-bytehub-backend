package com.qianyvbytehub.auth.infra.basic.service.impl;


import com.qianyvbytehub.auth.infra.basic.dao.AuthUserRoleDao;
import com.qianyvbytehub.auth.infra.basic.entity.AuthUserRole;
import com.qianyvbytehub.auth.infra.basic.service.AuthUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户角色关系表(AuthUserRole)表服务实现类
 *
 * @author makejava
 * @since 2024-12-13 10:04:00
 */
@Service("authUserRoleService")
public class AuthUserRoleServiceImpl implements AuthUserRoleService {
    @Resource
    private AuthUserRoleDao authUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthUserRole queryById(Long id) {
        return this.authUserRoleDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param authUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(AuthUserRole authUserRole) {

        return this.authUserRoleDao.insert(authUserRole);
    }

    /**
     * 修改数据
     *
     * @param authUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public AuthUserRole update(AuthUserRole authUserRole) {
        this.authUserRoleDao.update(authUserRole);
        return this.queryById(authUserRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authUserRoleDao.deleteById(id) > 0;
    }
}
