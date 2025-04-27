package com.qianyvbytehub.auth.infra.basic.service.impl;


import com.qianyvbytehub.auth.infra.basic.dao.AuthPermissionDao;
import com.qianyvbytehub.auth.infra.basic.entity.AuthPermission;
import com.qianyvbytehub.auth.infra.basic.service.AuthPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(AuthPermission)表服务实现类
 *
 * @author makejava
 * @since 2024-12-13 10:04:29
 */
@Service("authPermissionService")
public class AuthPermissionServiceImpl implements AuthPermissionService {
    @Resource
    private AuthPermissionDao authPermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthPermission queryById(Long id) {
        return this.authPermissionDao.queryById(id);
    }

    /**
     * 根据id批量查
     */
    @Override
    public List<AuthPermission> queryByIdList(List<Long> permissionIdList) {
        return this.authPermissionDao.queryByIdList(permissionIdList);
    }

    /**
     * 新增数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(AuthPermission authPermission) {

        return this.authPermissionDao.insert(authPermission);
    }

    /**
     * 修改数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(AuthPermission authPermission) {

        return this.authPermissionDao.update(authPermission);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authPermissionDao.deleteById(id) > 0;
    }


}
