package com.qianyvbytehub.auth.application.controller.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import com.qianyvbytehub.auth.application.controller.convert.AuthRoleDTOConverter;
import com.qianyvbytehub.auth.application.controller.entity.AuthRoleDTO;
import com.qianyvbytehub.auth.common.entity.Result;
import com.qianyvbytehub.auth.common.enums.DeletedStateEnum;
import com.qianyvbytehub.auth.domain.entity.AuthRoleBO;
import com.qianyvbytehub.auth.domain.service.AuthRoleDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/auth/role")
public class RoleController {

    @Resource
    private AuthRoleDomainService authRoleDomainService;


    /**
     * 新增角色
     * @param authRoleDTO
     * @return
     */
    @PostMapping("/add")
    public Result register(@RequestBody AuthRoleDTO authRoleDTO) {

        try {
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()),"角色名称不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()),"角色key不能为空");

            AuthRoleBO authRoleBO = AuthRoleDTOConverter
                    .INSTANCE.ConvertRoleDtoToBo(authRoleDTO);
            Boolean flag = authRoleDomainService.add(authRoleBO);
            if (flag) {
                return Result.ok("添加角色成功");
            }else{
                throw new RuntimeException("service层及以下导致注册失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Result.fail("添加角色失败");
        }
    }

    /**
     * 更新角色
     * @param authRoleDTO
     * @return
     */
    @DeleteMapping("/update")
    public Result update(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            Preconditions.checkNotNull(authRoleDTO.getId(),"主键不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter
                    .INSTANCE.ConvertRoleDtoToBo(authRoleDTO);
            Boolean flag = authRoleDomainService.update(authRoleBO);
            if (flag) {
                return Result.ok("更新成功");
            }else{
                throw new RuntimeException("service层及以下导致注册失败");
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage(),e);
            return Result.fail("更新角色数据失败");
        }


    }

    /**
     * 删除角色
     * @param authRoleDTO
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            Preconditions.checkNotNull(authRoleDTO.getId(),"主键不能为空");
            authRoleDTO.setIsDeleted(DeletedStateEnum.DELETED.getCode());
            AuthRoleBO authRoleBO = AuthRoleDTOConverter
                    .INSTANCE.ConvertRoleDtoToBo(authRoleDTO);
            Boolean flag = authRoleDomainService.update(authRoleBO);
            if (flag) {
                return Result.ok("删除角色成功");
            }else{
                throw new RuntimeException("service层及以下导致注册失败");
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage(),e);
            return Result.fail("删除角色数据失败");
        }
    }

}
