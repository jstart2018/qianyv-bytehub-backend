package com.qianyvbytehub.auth.application.controller.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户表(AuthUser)实体类
 *
 * @author makejava
 * @since 2024-12-11 19:50:06
 */
@Data
public class AuthUserDTO implements Serializable {
    private static final long serialVersionUID = 376773723147065490L;
/**
     * 主键
     */
    private Long id;
/**
     * 用户账号
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
     * 性别 1男 2女
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
     * 是否删除 0: 未删除 1: 已删除
     */
    private Integer isDeleted;


}

