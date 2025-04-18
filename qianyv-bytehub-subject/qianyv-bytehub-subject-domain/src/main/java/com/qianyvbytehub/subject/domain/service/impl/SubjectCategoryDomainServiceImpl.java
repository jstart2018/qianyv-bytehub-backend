package com.qianyvbytehub.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.qianyvbytehub.subject.domain.convert.SubjectCategoryConvert;
import com.qianyvbytehub.subject.domain.entity.SubjectCategoryBO;
import com.qianyvbytehub.subject.domain.service.SubjectCategoryDomainService;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectCategory;
import com.qianyvbytehub.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;


    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        if (log.isInfoEnabled()){
            log.info("SubjectCategoryDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory = SubjectCategoryConvert
                .INSTANCE.convertBoToCategory(subjectCategoryBO);

        subjectCategoryService.insert(subjectCategory);
    }

    /**
     * 查询大类
     * @param subjectCategoryBO
     * @return
     */
    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {

        SubjectCategory subjectCategory = SubjectCategoryConvert
                .INSTANCE.convertBoToCategory(subjectCategoryBO);
        //查询 满足条件的 大类
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryByAll(subjectCategory);

        log.info("SubjectCategoryDomainServiceImpl.queryCategory.subjectCategoryList:{}", JSON.toJSONString(subjectCategoryList));

        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConvert
                .INSTANCE.convertCategoryToBoList(subjectCategoryList);

        //查询大类下的题目  数量
        for (SubjectCategoryBO categoryBO : subjectCategoryBOList) {
            Long count = subjectCategoryService.querySubjectCountByCategoryId(categoryBO.getId());

            categoryBO.setSubjectCount(count);

        }

        return subjectCategoryBOList;
    }

    /**
     * 更新分类
     * @param subjectCategoryBO
     * @return
     */
    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConvert
                .INSTANCE.convertBoToCategory(subjectCategoryBO);
        int count = subjectCategoryService.update(subjectCategory);

        return count>0;
    }

    /**
     * 删除分类
     * @param subjectCategoryBO
     * @return
     */
    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        subjectCategoryBO.setIsDeleted(1);
        log.info("SubjectCategoryController.delete.bo:{}", JSON.toJSONString(subjectCategoryBO));
        SubjectCategory subjectCategory = SubjectCategoryConvert
                .INSTANCE.convertBoToCategory(subjectCategoryBO);
        int count = subjectCategoryService.update(subjectCategory);

        return count>0;
    }
}
