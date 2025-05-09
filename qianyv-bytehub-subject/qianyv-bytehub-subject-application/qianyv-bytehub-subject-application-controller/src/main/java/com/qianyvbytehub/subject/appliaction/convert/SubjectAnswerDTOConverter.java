package com.qianyvbytehub.subject.appliaction.convert;


import com.qianyvbytehub.subject.appliaction.entity.SubjectAnswerDTO;
import com.qianyvbytehub.subject.domain.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * 题目答案dto转换器
 * 
 * @author: ChickenWing
 * @date: 2023/10/8
 */
@Mapper
public interface SubjectAnswerDTOConverter {

    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    SubjectAnswerBO convertDTOToBO(SubjectAnswerDTO subjectAnswerDTO);

    List<SubjectAnswerBO> convertListDTOToBO(List<SubjectAnswerDTO> dtoList);

}
