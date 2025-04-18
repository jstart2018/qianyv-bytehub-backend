package com.qianyvbytehub.subject.domain.convert;


import com.qianyvbytehub.subject.domain.entity.SubjectAnswerBO;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MultipleSubjectConverter {

    //MultipleSubjectConverter INSTANCE = Mappers.getMapper(MultipleSubjectConverter.class);
    MultipleSubjectConverter INSTANCE = Mappers.getMapper(MultipleSubjectConverter.class);

    SubjectMultiple convertAnswerBoToEntity(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertEntityToAnswerBoList(List<SubjectMultiple> subjectMultipleList);

}
