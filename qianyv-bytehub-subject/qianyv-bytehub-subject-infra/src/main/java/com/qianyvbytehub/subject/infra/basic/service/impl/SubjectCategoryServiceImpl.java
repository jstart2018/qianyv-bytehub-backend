package com.qianyvbytehub.subject.infra.basic.service.impl;


import com.alibaba.fastjson.JSON;
import com.qianyvbytehub.subject.infra.basic.dao.SubjectCategoryDao;
import com.qianyvbytehub.subject.infra.basic.dao.SubjectMappingDao;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectCategory;
import com.qianyvbytehub.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目分类(SubjectCategory)表服务实现类
 *
 * @author makejava
 * @since 2024-11-23 15:05:55
 */
@Service("subjectCategoryService")
@Slf4j
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
    @Resource
    private SubjectCategoryDao subjectCategoryDao;
    private SubjectMappingDao subjectMappingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectCategory queryById(Long id) {
        return this.subjectCategoryDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param subjectCategory 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */

    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectCategory insert(SubjectCategory subjectCategory) {
        this.subjectCategoryDao.insert(subjectCategory);
        return subjectCategory;
    }

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SubjectCategory subjectCategory) {
        if (log.isInfoEnabled()){
            log.info("SubjectCategoryController.add.Category:{}", JSON.toJSONString(subjectCategory));
        }
        int update = this.subjectCategoryDao.update(subjectCategory);
        return update;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectCategoryDao.deleteById(id) > 0;
    }

    /**
     * 根据条件查询
     * @return
     */
    @Override
    public List<SubjectCategory> queryByAll(SubjectCategory subjectCategory) {
        List<SubjectCategory> subjectCategoryList = subjectCategoryDao.queryByAll(subjectCategory);
        log.info("SubjectCategoryBasic.queryByAll.subjectCategory:{}", JSON.toJSONString(subjectCategory));

        return subjectCategoryList;
    }

    /**
     * 查询大类下题目数量
     * @param id
     * @return
     */
    @Override
    public Long querySubjectCountByCategoryId(Long id) {
        return this.subjectMappingDao.querySubjectCountByCategoryId(id);
    }
}
