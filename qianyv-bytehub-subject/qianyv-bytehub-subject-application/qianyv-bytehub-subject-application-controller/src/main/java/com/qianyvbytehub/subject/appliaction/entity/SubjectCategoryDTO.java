package com.qianyvbytehub.subject.appliaction.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目分类(SubjectCategory)实体类
 *
 * @author makejava
 * @since 2024-11-23 15:05:51
 */
@Data
public class SubjectCategoryDTO implements Serializable {
    private static final long serialVersionUID = 537643869303109278L;
/**
     * 主键
     */
    private Long id;
/**
     * 分类名称
     */
    private String categoryName;
/**
     * 分类类型
     */
    private Integer categoryType;
/**
     * 图标连接
     */
    private String imageUrl;
/**
     * 父级id
     */
    private Long parentId;

/**
     * 是否删除 0: 未删除 1: 已删除
     */
    private Integer isDeleted;

    /**
     * 大类下题目数量
     */
    private Long subjectCount;



}

