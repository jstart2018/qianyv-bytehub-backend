package com.qianyvbytehub.auth.application.controller.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色关系表(AuthUserRole)实体类
 *
 * @author makejava
 * @since 2024-12-13 10:04:00
 */
@Data
public class AuthUserRoleDTO implements Serializable {
    private static final long serialVersionUID = -61493475509627960L;
/**
     * 主键
     */
    private Long id;
/**
     * 用户id
     */
    private Long userId;
/**
     * 角色id
     */
    private Long roleId;
/**
     * 创建人
     */
    private String createdBy;
/**
     * 创建时间
     */
    private Date createdTime;
/**
     * 更新人
     */
    private String updateBy;
/**
     * 更新时间
     */
    private Date updateTime;
/**
     * 是否删除 0: 未删除 1: 已删除
     */
    private Integer isDeleted;


}

