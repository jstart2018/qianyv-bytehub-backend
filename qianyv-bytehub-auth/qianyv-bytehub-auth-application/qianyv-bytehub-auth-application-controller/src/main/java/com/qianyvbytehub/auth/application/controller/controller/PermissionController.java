package com.qianyvbytehub.auth.application.controller.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import com.qianyvbytehub.auth.application.controller.convert.AuthPermissionDTOConverter;
import com.qianyvbytehub.auth.application.controller.entity.AuthPermissionDTO;
import com.qianyvbytehub.auth.common.entity.Result;
import com.qianyvbytehub.auth.common.enums.DeletedStateEnum;
import com.qianyvbytehub.auth.common.enums.UseStateEnum;
import com.qianyvbytehub.auth.domain.entity.AuthPermissionBO;
import com.qianyvbytehub.auth.domain.service.AuthPermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/auth/permission")
public class PermissionController {

    @Resource
    private AuthPermissionDomainService authPermissionDomainService;


    /**
     * 新增权限
     * @param authPermissionDTO
     * @return
     */
    @PostMapping("/add")
    public Result register(@RequestBody AuthPermissionDTO authPermissionDTO) {

        try {
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getName()),"权限名称不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getPermissionKey()),"权限唯一标识不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getType(),"权限类型不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getParentId(),"父级id不能为空");

            authPermissionDTO.setStatus(UseStateEnum.ENABLE.getCode());
            authPermissionDTO.setShow(0);

            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter
                    .INSTANCE.ConvertPermissionDtoToBo(authPermissionDTO);
            Boolean flag = authPermissionDomainService.add(authPermissionBO);
            if (flag) {
                return Result.ok("添加权限成功");
            }else{
                throw new RuntimeException("service层及以下导致注册失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Result.fail("添加权限失败");
        }
    }

    /**
     * 更新权限
     * @param authPermissionDTO
     * @return
     */
    @DeleteMapping("/update")
    public Result update(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            Preconditions.checkNotNull(authPermissionDTO.getId(),"主键不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter
                    .INSTANCE.ConvertPermissionDtoToBo(authPermissionDTO);
            Boolean flag = authPermissionDomainService.update(authPermissionBO);
            if (flag) {
                return Result.ok("更新成功");
            }else{
                throw new RuntimeException("service层及以下导致注册失败");
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage(),e);
            return Result.fail("更新权限数据失败");
        }


    }

    /**
     * 删除权限
     * @param authPermissionDTO
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            Preconditions.checkNotNull(authPermissionDTO.getId(),"主键不能为空");

            authPermissionDTO.setIsDeleted(DeletedStateEnum.DELETED.getCode());
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter
                            .INSTANCE.ConvertPermissionDtoToBo(authPermissionDTO);
            Boolean flag = authPermissionDomainService.update(authPermissionBO);
            if (flag) {
                return Result.ok("删除权限成功");
            }else{
                throw new RuntimeException("service层及以下导致注册失败");
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage(),e);
            return Result.fail("删除权限数据失败");
        }
    }

}
