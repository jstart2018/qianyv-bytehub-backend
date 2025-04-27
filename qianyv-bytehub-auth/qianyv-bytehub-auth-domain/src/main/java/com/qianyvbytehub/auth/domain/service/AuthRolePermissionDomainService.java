package com.qianyvbytehub.auth.domain.service;


import com.qianyvbytehub.auth.domain.entity.AuthRoleBO;
import com.qianyvbytehub.auth.infra.basic.entity.AuthRole;

import java.util.List;

public interface AuthRolePermissionDomainService {

    /**
     * 新增角色、权限关联数据
     * @param permissionIds
     * @param authRole
     * @return
     */
    Integer add(AuthRole authRole, List<Long> permissionIds);

    /**
     * 更新角色、权限关联数据 (包括‘删除’字段：is_delete)
     * @param authRoleBO
     * @return
     */
    Boolean update(AuthRoleBO authRoleBO);
}
