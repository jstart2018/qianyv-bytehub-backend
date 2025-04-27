package com.qianyvbytehub.auth.domain.service;


import com.qianyvbytehub.auth.domain.entity.AuthRoleBO;

public interface AuthRoleDomainService {

    /**
     * 新增角色
     * @param authRoleBO
     * @return
     */
    Boolean add(AuthRoleBO authRoleBO);

    /**
     * 更新角色数据(包括‘删除’字段：is_delete)
     * @param authRoleBO
     * @return
     */
    Boolean update(AuthRoleBO authRoleBO);
}
