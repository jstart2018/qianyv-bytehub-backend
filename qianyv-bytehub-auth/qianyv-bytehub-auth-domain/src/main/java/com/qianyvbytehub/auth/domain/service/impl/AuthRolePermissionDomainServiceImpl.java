package com.qianyvbytehub.auth.domain.service.impl;


import com.qianyvbytehub.auth.domain.convert.AuthRoleBOConverter;
import com.qianyvbytehub.auth.domain.entity.AuthRoleBO;
import com.qianyvbytehub.auth.domain.service.AuthRolePermissionDomainService;
import com.qianyvbytehub.auth.infra.basic.entity.AuthRole;
import com.qianyvbytehub.auth.infra.basic.entity.AuthRolePermission;
import com.qianyvbytehub.auth.infra.basic.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    /**
     * 新增角色，关联权限
     * @param authRole
     * @param permissionIds
     * @return
     */
    @Override
    public Integer add(AuthRole authRole, List<Long> permissionIds) {
        List<AuthRolePermission> rolePermissions = new LinkedList<>();
        permissionIds.forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(authRole.getId());
            authRolePermission.setPermissionId(permissionId);

            rolePermissions.add(authRolePermission);
        });
        Integer count = authRolePermissionService.insertBatch(rolePermissions);
        return count;
    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConverter
                .INSTANCE.ConvertRoleBoToEntity(authRoleBO);

        return null;
    }
}
