package com.qianyvbytehub.subject.domain.handle.subject;


import com.qianyvbytehub.subject.common.enums.DeletedStateEnum;
import com.qianyvbytehub.subject.common.enums.SubjectTypeEnum;
import com.qianyvbytehub.subject.domain.convert.JudgeSubjectConverter;
import com.qianyvbytehub.subject.domain.entity.SubjectAnswerBO;
import com.qianyvbytehub.subject.domain.entity.SubjectInfoBO;
import com.qianyvbytehub.subject.domain.entity.SubjectOptionBO;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectJudge;
import com.qianyvbytehub.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Component
public class JudgeTypeHandler implements SubjectTypeHandler{
    @Resource
    private SubjectJudgeService subjectJudgeService;


    @Override
    public SubjectTypeEnum getSubjectType() {
        return SubjectTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //新增判断题目的逻辑
        //TODO 待参数校验,可能会空指针异常
        List<SubjectJudge> subjectJudgeList = new LinkedList<>();
        subjectInfoBO.getOptionList().forEach(
                option ->{
                    //逐个将AnswerBo转成对应题目的实体类，添加选项
                    SubjectJudge subjectJudge = JudgeSubjectConverter
                            .INSTANCE.convertAnswerBoToEntity(option);
                    //选项所属的题目id
                    subjectJudge.setSubjectId(subjectInfoBO.getId());
                    subjectJudge.setIsDeleted(DeletedStateEnum.UNDELETED.getCode());
                    subjectJudgeList.add(subjectJudge);
                });
        subjectJudgeService.batchInsert(subjectJudgeList);
    }

    /**
     * 判断题查询
     */
    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId(subjectId);

        //查询SubjectJudge表
        List<SubjectJudge> subjectJudgeList = subjectJudgeService.queryByCondition(subjectJudge);

        //转
        List<SubjectAnswerBO> subjectAnswerBOS = JudgeSubjectConverter
                .INSTANCE.convertEntityToAnswerBo(subjectJudgeList);
        //set进SubjectOptionBO
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOS);

        return subjectOptionBO;

    }
}
