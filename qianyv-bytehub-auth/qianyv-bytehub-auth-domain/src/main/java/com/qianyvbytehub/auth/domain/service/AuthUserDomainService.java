package com.qianyvbytehub.auth.domain.service;


import com.qianyvbytehub.auth.domain.entity.AuthUserBo;

public interface AuthUserDomainService {

    /**
     * 新增用户
     * @param authUserBo
     * @return
     */
    Boolean regist(AuthUserBo authUserBo);

    /**
     * 查用户详情
     * @param authUserBo
     * @return
     */
    AuthUserBo getUserInfo(AuthUserBo authUserBo);

    /**
     * 更新用户数据(包括‘删除’字段：is_delete)
     * @param authUserBo
     * @return
     */
    Boolean update(AuthUserBo authUserBo);

    Boolean dologin(String openId);
}
