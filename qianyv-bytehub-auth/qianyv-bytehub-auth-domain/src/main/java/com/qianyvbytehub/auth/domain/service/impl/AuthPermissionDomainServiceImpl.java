package com.qianyvbytehub.auth.domain.service.impl;


import com.qianyvbytehub.auth.domain.convert.AuthPermissionBOConverter;
import com.qianyvbytehub.auth.domain.entity.AuthPermissionBO;
import com.qianyvbytehub.auth.domain.service.AuthPermissionDomainService;
import com.qianyvbytehub.auth.infra.basic.entity.AuthPermission;
import com.qianyvbytehub.auth.infra.basic.service.AuthPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {
    @Resource
    private AuthPermissionService authPermissionService;

    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {

        AuthPermission authPermission = AuthPermissionBOConverter
                .INSTANCE.ConvertPermissionBoToEntity(authPermissionBO);
        Integer count = authPermissionService.insert(authPermission);

        return count>0;
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter
                .INSTANCE.ConvertPermissionBoToEntity(authPermissionBO);
        Integer count = authPermissionService.update(authPermission);

        return count>0;
    }
}
