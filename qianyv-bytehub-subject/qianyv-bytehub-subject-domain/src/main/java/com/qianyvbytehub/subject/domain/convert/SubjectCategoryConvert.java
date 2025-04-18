package com.qianyvbytehub.subject.domain.convert;


import com.qianyvbytehub.subject.domain.entity.SubjectCategoryBO;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface SubjectCategoryConvert {

    SubjectCategoryConvert INSTANCE  = Mappers.getMapper(SubjectCategoryConvert.class);

    SubjectCategory convertBoToCategory(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> convertCategoryToBoList(List<SubjectCategory> subjectCategoryList);

}
