package com.qianyvbytehub.subject.domain.convert;


import com.qianyvbytehub.subject.domain.entity.SubjectAnswerBO;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectBrief;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BriefSubjectConverter {

    //MultipleSubjectConverter INSTANCE = Mappers.getMapper(MultipleSubjectConverter.class);
    BriefSubjectConverter INSTANCE = Mappers.getMapper(BriefSubjectConverter.class);

    SubjectBrief convertAnswerBoToEntity(SubjectAnswerBO subjectAnswerBO);



}
