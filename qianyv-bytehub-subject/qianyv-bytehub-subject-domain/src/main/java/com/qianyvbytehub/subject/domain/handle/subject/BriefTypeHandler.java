package com.qianyvbytehub.subject.domain.handle.subject;


import com.qianyvbytehub.subject.common.enums.SubjectTypeEnum;
import com.qianyvbytehub.subject.domain.entity.SubjectInfoBO;
import com.qianyvbytehub.subject.domain.entity.SubjectOptionBO;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectBrief;
import com.qianyvbytehub.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BriefTypeHandler implements SubjectTypeHandler{
    @Resource
    private SubjectBriefService subjectBriefService;


    @Override
    public SubjectTypeEnum getSubjectType() {
        return SubjectTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        //简答题没有选项，所以插入数据库也只是插入一条，且subjectAnswer也不在集合里面，而是单独一个属性
        // 不用再遍历什么那么麻烦
        subjectBrief.setSubjectAnswer(subjectInfoBO.getSubjectAnswer());

        subjectBriefService.insert(subjectBrief);

    }

    /**
     * 简答题详情查询
     */
    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectId);
        //查SubjectBrief表，因为简答题没有选项，所以只返回一个实体，并不返回集合
        SubjectBrief sb = subjectBriefService.queryByCondition(subjectBrief);

        //此处也不用再转成数目AnswerBo了，因为要返回的SubjectOptionBO直接有subjectAnswer这个属性

        //set进SubjectOptionBO
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(sb.getSubjectAnswer());

        return subjectOptionBO;

    }

}
