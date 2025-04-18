package com.qianyvbytehub.subject.infra.basic.service;


import com.qianyvbytehub.subject.infra.basic.entity.SubjectMapping;

import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表服务接口
 *
 * @author makejava
 * @since 2024-11-25 19:38:46
 */
public interface SubjectMappingService {

    /**
     * 根据id查单条数据
     * @param id 主键
     * @return 实例对象
     */
    SubjectMapping queryById(Long id);

    /**
     * 分页查询
     *
     * @param subjectMapping 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */

    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    SubjectMapping insert(SubjectMapping subjectMapping);

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    SubjectMapping update(SubjectMapping subjectMapping);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


    /**
     * 查询标签id
     */
    List<Long> queryLabelId(SubjectMapping subjectMapping);

    /**
     * 批量插入
     */
    void batchInsert(List<SubjectMapping> mappingList);


    /**
     * 条件查询题目
     * @param subjectMapping
     * @return
     */
    List<SubjectMapping> queryByAll(SubjectMapping subjectMapping);
}
