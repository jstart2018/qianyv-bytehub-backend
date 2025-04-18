package com.qianyvbytehub.subject.domain.service;


import com.qianyvbytehub.subject.common.entity.PageResult;
import com.qianyvbytehub.subject.domain.entity.SubjectInfoBO;

/**
 * 题目领域服务
 * 
 * @author: ChickenWing
 * @date: 2023/10/3
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 批量查询题目
     * @param subjectInfoBO
     * @return
     */
    PageResult<SubjectInfoBO> querySubjectList(SubjectInfoBO subjectInfoBO);

    /**
     * 查询题目详情
     * @param subjectInfoBO
     * @return
     */
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);
}

