package com.qianyvbytehub.auth.domain.service.impl;


import com.qianyvbytehub.auth.domain.convert.AuthRoleBOConverter;
import com.qianyvbytehub.auth.domain.entity.AuthRoleBO;
import com.qianyvbytehub.auth.domain.service.AuthRoleDomainService;
import com.qianyvbytehub.auth.domain.service.AuthRolePermissionDomainService;
import com.qianyvbytehub.auth.infra.basic.entity.AuthRole;
import com.qianyvbytehub.auth.infra.basic.service.AuthRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {
    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;
    @Resource
    private AuthRoleService authRoleService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(AuthRoleBO authRoleBO) {

        AuthRole authRole = AuthRoleBOConverter
                .INSTANCE.ConvertRoleBoToEntity(authRoleBO);
        Integer count1 = authRoleService.insert(authRole);

        //给角色赋予 响应的权限：
        List<Long> permissionIds = new LinkedList<>();
        permissionIds.add(1L);
        permissionIds.add(2L);
        permissionIds.add(3L);

        Integer count2 = authRolePermissionDomainService.add(authRole, permissionIds);

        return count1>0 && count2>0;
    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConverter
                .INSTANCE.ConvertRoleBoToEntity(authRoleBO);
        Integer count = authRoleService.update(authRole);

        return count>0;
    }
}
