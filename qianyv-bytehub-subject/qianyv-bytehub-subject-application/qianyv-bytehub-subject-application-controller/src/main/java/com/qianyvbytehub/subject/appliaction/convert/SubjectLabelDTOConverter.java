package com.qianyvbytehub.subject.appliaction.convert;


import com.qianyvbytehub.subject.appliaction.entity.SubjectLabelDTO;
import com.qianyvbytehub.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 标签dto的转换
 * 
 * @author: ChickenWing
 * @date: 2023/10/3
 */
@Mapper
public interface SubjectLabelDTOConverter {

    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertDtoToLabelBO(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> convertBOToLabelDTOList(List<SubjectLabelBO> boList);

}
