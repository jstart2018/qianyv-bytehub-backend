package com.qianyvbytehub.subject.appliaction.convert;


import com.qianyvbytehub.subject.appliaction.entity.SubjectCategoryDTO;
import com.qianyvbytehub.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface SubjectCategoryDTOConvert {

    SubjectCategoryDTOConvert INSTANCE  = Mappers.getMapper(SubjectCategoryDTOConvert.class);

    SubjectCategoryBO convertDtoToBo(SubjectCategoryDTO subjectCategoryDTO);

    List<SubjectCategoryDTO> convertBoToDtoList(List<SubjectCategoryBO> subjectCategoryBOList);

}
