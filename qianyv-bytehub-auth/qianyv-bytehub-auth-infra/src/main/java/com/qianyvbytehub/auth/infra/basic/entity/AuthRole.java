package com.qianyvbytehub.auth.infra.basic.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表(AuthRole)实体类
 *
 * @author makejava
 * @since 2024-12-13 10:03:24
 */
@Data
public class AuthRole implements Serializable {
    private static final long serialVersionUID = 978947052842109230L;
/**
     * 主键
     */
    private Long id;
/**
     * 角色名称
     */
    private String roleName;
/**
     * 唯一标识
     */
    private String roleKey;
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

