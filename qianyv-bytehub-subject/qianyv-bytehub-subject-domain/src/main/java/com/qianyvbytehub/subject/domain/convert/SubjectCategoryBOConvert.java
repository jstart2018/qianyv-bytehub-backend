package com.qianyvbytehub.subject.domain.convert;

import com.qianyvbytehub.subject.domain.entity.SubjectCategoryBO;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectCategoryBOConvert {

    SubjectCategoryBOConvert INSTANCE = Mappers.getMapper(SubjectCategoryBOConvert.class);

    SubjectCategory BOtoPO(SubjectCategoryBO subjectCategoryBO);

}
