package com.qianyvbytehub.subject.domain.handle.subject;


import com.qianyvbytehub.subject.common.enums.SubjectTypeEnum;
import com.qianyvbytehub.subject.domain.entity.SubjectInfoBO;
import com.qianyvbytehub.subject.domain.entity.SubjectOptionBO;

public interface SubjectTypeHandler {

    //获取题目类型的对应枚举，每个题目在内部自己提供
    SubjectTypeEnum getSubjectType();


    //各种题目都要实现的新增逻辑
    void add(SubjectInfoBO subjectInfoBO);

    //查询各自题目的 选项或各个选项的答案内容
    SubjectOptionBO query(Long subjectId);

}
