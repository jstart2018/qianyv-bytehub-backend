package com.qianyvbytehub.gateway.auth;


import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.qianyvbytehub.gateway.entity.AuthPermission;
import com.qianyvbytehub.gateway.entity.AuthRole;
import com.qianyvbytehub.gateway.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义权限验证接口扩展 
 */
@Component
@Slf4j
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private RedisUtil redisUtil;
    //权限前缀
    private String authPermissionPrefix = "auth.permission";
    //角色前缀
    private String authRolePrefix = "auth.role";


    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        List<String> roleList = getAuth(authPermissionPrefix, loginId.toString());

        return roleList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        List<String> roleList = getAuth(authRolePrefix, loginId.toString());

        return roleList;
    }

    public List<String> getAuth(String prefix, String loginId){
        //从redis中获取值
        String roleKey = redisUtil.buildKey(prefix, loginId.toString());
        Object o = redisUtil.get(roleKey);
        String authValue = o == null ? "" : o.toString();

        List<String> authList = new LinkedList<>();

        //不同的前缀代表不同的校验不同的东西（角色或权限），需要返回的东西也不同
        if(prefix.equals(authRolePrefix)){ //如果是角色,返回roleKey
            List<AuthRole> authRoles = JSON.parseObject
                    (authValue, new TypeReference<List<AuthRole>>() {});
            List<String> roleValue = authRoles.stream()
                    .map(AuthRole::getRoleKey).collect(Collectors.toList());
            authList.addAll(roleValue);
        }else if(prefix.equals(authPermissionPrefix)){ //如果是权限,返回permissionKey
            List<AuthPermission> authRoles = JSON.parseObject
                    (authValue, new TypeReference<List<AuthPermission>>() {});
            List<String> permissionValue = authRoles.stream()
                    .map(AuthPermission::getPermissionKey).collect(Collectors.toList());
            authList.addAll(permissionValue);
        }

        return authList;
    }
}
