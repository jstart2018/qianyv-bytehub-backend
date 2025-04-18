package com.qianyvbytehub.subject.domain.handle.subject;


import com.qianyvbytehub.subject.common.enums.DeletedStateEnum;
import com.qianyvbytehub.subject.common.enums.SubjectTypeEnum;
import com.qianyvbytehub.subject.domain.convert.MultipleSubjectConverter;
import com.qianyvbytehub.subject.domain.entity.SubjectAnswerBO;
import com.qianyvbytehub.subject.domain.entity.SubjectInfoBO;
import com.qianyvbytehub.subject.domain.entity.SubjectOptionBO;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectMultiple;
import com.qianyvbytehub.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Component
public class MultipleTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectTypeEnum getSubjectType() {
        return SubjectTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //新增多选题目的逻辑
        //TODO 待参数校验,可能会空指针异常
        List<SubjectMultiple> subjectMultipleList = new LinkedList<>();
        subjectInfoBO.getOptionList().forEach(
                option ->{
                    //逐个将AnswerBo转成对应题目的实体类，添加选项
                    SubjectMultiple subjectMultiple = MultipleSubjectConverter
                            .INSTANCE.convertAnswerBoToEntity(option);
                    //选项所属的题目id
                    subjectMultiple.setSubjectId(subjectInfoBO.getId());
                    subjectMultiple.setIsDeleted(DeletedStateEnum.UNDELETED.getCode());
                    subjectMultipleList.add(subjectMultiple);
                });
        subjectMultipleService.batchInsert(subjectMultipleList);

    }

    /**
     * 多选题目详情查询
     */
    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectMultiple subjectMultiple = new SubjectMultiple();
        subjectMultiple.setSubjectId(subjectId);
        //去多选表中查询得到 SubjectMultiple
        List<SubjectMultiple> subjectMultipleList = subjectMultipleService.queryByCondition(subjectMultiple);

        //转成SubjectAnswerBO
        List<SubjectAnswerBO> subjectAnswerBOS = MultipleSubjectConverter
                .INSTANCE.convertEntityToAnswerBoList(subjectMultipleList);

        //set进需要返回的SubjectOptionBO
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOS);

        return subjectOptionBO;
    }
}
