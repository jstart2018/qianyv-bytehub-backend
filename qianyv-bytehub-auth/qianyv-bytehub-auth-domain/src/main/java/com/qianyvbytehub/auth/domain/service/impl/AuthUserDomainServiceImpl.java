package com.qianyvbytehub.auth.domain.service.impl;


import cn.dev33.satoken.secure.SaSecureUtil;
import com.alibaba.fastjson.JSON;
import com.qianyvbytehub.auth.common.enums.DeletedStateEnum;
import com.qianyvbytehub.auth.domain.convert.AuthUserBOConverter;
import com.qianyvbytehub.auth.domain.convert.AuthUserRoleBOConverter;
import com.qianyvbytehub.auth.domain.entity.AuthUserBo;
import com.qianyvbytehub.auth.domain.entity.AuthUserRoleBO;
import com.qianyvbytehub.auth.domain.service.AuthUserDomainService;
import com.qianyvbytehub.auth.domain.utils.RedisUtil;
import com.qianyvbytehub.auth.infra.basic.entity.*;
import com.qianyvbytehub.auth.infra.basic.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthUserRoleService authUserRoleService;

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private RedisUtil redisUtil;

    //盐
    private String salt = "jstart";

    //角色前缀
    private String authRolePrefix = "auth.role";

    //权限前缀
    private String authPermissionPrefix = "auth.permission";



    /**
     * 新增用户
     * @param authUserBo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean regist(AuthUserBo authUserBo) {
        authUserBo.setStatus(0);
        authUserBo.setIsDeleted(DeletedStateEnum.UNDELETED.getCode());

        //可能是用验证码登录时注册的，此时没有传入密码，就不需要加盐，否则会空指针
        if (authUserBo.getPassword() != null) {
            //给摘要算法MD5 加盐：使其更不容易被解析出来
            authUserBo.setPassword(SaSecureUtil.md5BySalt(authUserBo.getPassword(),salt));
        }

        AuthUser authUser = AuthUserBOConverter.
                INSTANCE.ConvertBoToEntity(authUserBo);
        //先去数据库查有没有该用户再注册，避免重复注册
        List<AuthUser> authUserList = authUserService.queryByCondition(authUser);
        if (authUserList.size() > 0) {
            return true;
        }
        Integer count1 = authUserService.insert(authUser); //新增完后要返回主键字段

        //给角色添加默认的角色normal_user

        // 关联用户的 角色和权限表
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey("normal_user");
        AuthRole role = authRoleService.queryByCondition(authRole);//查角色表中普通角色的信息

        //封装用户、角色关联表
        AuthUserRoleBO authUserRoleBO = new AuthUserRoleBO();
        authUserRoleBO.setUserId(authUser.getId());
        authUserRoleBO.setRoleId(role.getId());
        authUserRoleBO.setIsDeleted(DeletedStateEnum.UNDELETED.getCode());
        AuthUserRole userRole = AuthUserRoleBOConverter
                .INSTANCE.ConvertUserRoleBoToEntity(authUserRoleBO);
        Integer count2 = authUserRoleService.insert(userRole);

        //把该用户的角色和角色对应的权限都放入缓存redis中（需要配合校验规则来存放key和value）：

        //先放角色：
        String roleKey = redisUtil.buildKey(authRolePrefix, authUserBo.getUserName());
        List<AuthRole> roleList = new LinkedList<>();
        roleList.add(role);
        redisUtil.set(roleKey, JSON.toJSONString(roleList));

        //再放权限
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, authUserBo.getUserName());
        //根据roleId查权限id
        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(role.getId());
        List<AuthRolePermission> rolePermissions = authRolePermissionService.queryByCondition(authRolePermission);
        //根据权限id查权限全部内容
        List<Long> permissionIdList = rolePermissions
                .stream()
                .map(AuthRolePermission::getPermissionId)
                .collect(Collectors.toList());
        List<AuthPermission> permissionList = authPermissionService.queryByIdList(permissionIdList);
        //放入redis
        redisUtil.set(permissionKey, JSON.toJSONString(permissionList));

        return count1 > 0 && count2 > 0;
    }

    /**
     * 查用户详情
     * @param authUserBo
     * @return
     */
    @Override
    public AuthUserBo getUserInfo(AuthUserBo authUserBo) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.ConvertBoToEntity(authUserBo);

        List<AuthUser> authUsers = authUserService.queryByCondition(authUser);
        if (authUsers.size() > 0) {
            AuthUserBo authUserBoResult = AuthUserBOConverter
                    .INSTANCE.ConvertEntityToBo(authUsers.get(0));
            return authUserBoResult;
        }

        return null;
    }

    /**
     * 更改
     * @param authUserBo
     * @return
     */
    @Override
    public Boolean update(AuthUserBo authUserBo) {

        AuthUser authUser = AuthUserBOConverter.
                INSTANCE.ConvertBoToEntity(authUserBo);
        Integer count = authUserService.update(authUser);

        //TODO 需要关联用户的 角色和权限表

        return count > 0;
    }

    /**
     * 登录
     * @param openId
     * @return
     */
    @Override
    public Boolean dologin(String openId) {
        AuthUserBo authUserBo = new AuthUserBo();
        authUserBo.setUserName(openId);//userName作为openId

        Boolean result = regist(authUserBo);

        return result;

    }
}
