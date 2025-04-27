package com.qianyvbytehub.auth.domain.service;


import com.qianyvbytehub.auth.domain.entity.AuthPermissionBO;

public interface AuthPermissionDomainService {

    /**
     * 新增用户
     * @param authPermissionBO
     * @return
     */
    Boolean add(AuthPermissionBO authPermissionBO);

    /**
     * 更新用户数据(包括‘删除’字段：is_delete)
     * @param authPermissionBO
     * @return
     */
    Boolean update(AuthPermissionBO authPermissionBO);
}
