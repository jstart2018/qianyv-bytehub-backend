package com.qianyvbytehub.subject.domain.convert;

import com.qianyvbytehub.subject.domain.entity.SubjectMappingBO;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectMappingConverter {

    SubjectMappingConverter INSTANCE = Mappers.getMapper(SubjectMappingConverter.class);

    SubjectMapping convertBoToMapping(SubjectMappingBO subjectMappingBO);


}
