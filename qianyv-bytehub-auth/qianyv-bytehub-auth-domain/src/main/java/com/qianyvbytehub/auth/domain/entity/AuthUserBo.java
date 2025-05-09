package com.qianyvbytehub.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(AuthUser)实体类
 *
 * @author makejava
 * @since 2024-12-11 19:50:06
 */
@Data
public class AuthUserBo implements Serializable {
    private static final long serialVersionUID = 376773723147065490L;
/**
     * 主键
     */
    private Long id;
/**
     * 用户名称/账号
     */
    private String userName;
/**
     * 昵称
     */
    private String nickName;
/**
     * 邮箱
     */
    private String email;
/**
     * 手机号
     */
    private String phone;
/**
     * 密码
     */
    private String password;
/**
     * 性别
     */
    private Integer sex;
/**
     * 头像
     */
    private String avatar;
/**
     * 状态 0启用 1禁用
     */
    private Integer status;
/**
     * 个人介绍
     */
    private String introduce;
/**
     * 特殊字段
     */
    private String extJson;
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

