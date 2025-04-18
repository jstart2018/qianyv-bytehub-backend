package com.qianyvbytehub.subject.domain.handle.subject;


import com.qianyvbytehub.subject.common.enums.DeletedStateEnum;
import com.qianyvbytehub.subject.common.enums.SubjectTypeEnum;
import com.qianyvbytehub.subject.domain.convert.RadioSubjectConverter;
import com.qianyvbytehub.subject.domain.entity.SubjectAnswerBO;
import com.qianyvbytehub.subject.domain.entity.SubjectInfoBO;
import com.qianyvbytehub.subject.domain.entity.SubjectOptionBO;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectRadio;
import com.qianyvbytehub.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Component
public class RadioTypeHandler implements SubjectTypeHandler{
    @Resource
    private SubjectRadioService subjectRadioService;


    @Override
    public SubjectTypeEnum getSubjectType() {
        return SubjectTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //新增单选题目的逻辑
        //TODO 待参数校验,可能会空指针异常
        List<SubjectRadio> subjectRadioList = new LinkedList<>();
        subjectInfoBO.getOptionList().forEach(
                option ->{
                    //添加选项
                    SubjectRadio subjectRadio = RadioSubjectConverter
                            .INSTANCE.convertAnswerBoToEntity(option);
                    //选项所属的题目id
                    subjectRadio.setSubjectId(subjectInfoBO.getId());
                    subjectRadio.setIsDeleted(DeletedStateEnum.UNDELETED.getCode());
                    subjectRadioList.add(subjectRadio);
                });
        subjectRadioService.batchInsert(subjectRadioList);

    }

    /**
     * 查询单选题目的选项内容：
     * @param subjectId
     */
    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectRadio subjectRadio = new SubjectRadio();
        subjectRadio.setSubjectId(subjectId);
        subjectRadio.setIsDeleted(DeletedStateEnum.UNDELETED.getCode());
        List<SubjectRadio> subjectRadioList =
                subjectRadioService.queryByCondition(subjectRadio);
        //转成subjectAnswerBO的集合
        List<SubjectAnswerBO> subjectAnswerBOS = RadioSubjectConverter
                .INSTANCE.convertEntityToAnswerBoList(subjectRadioList);

        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOS);
        return subjectOptionBO;
    }
}
