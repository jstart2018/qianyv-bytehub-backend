package com.qianyvbytehub.subject.domain.convert;

import com.qianyvbytehub.subject.domain.entity.SubjectInfoBO;
import com.qianyvbytehub.subject.domain.entity.SubjectMappingBO;
import com.qianyvbytehub.subject.domain.entity.SubjectOptionBO;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBoToInfo(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO convertOptionToBo(SubjectOptionBO subjectOptionBO);

    SubjectInfoBO convertOptionAndInfoToBo(SubjectOptionBO subjectOptionBO,SubjectInfo subjectInfo);

    List<SubjectInfoBO> convertListInfoToBO(List<SubjectInfo> subjectInfoList);

    SubjectMappingBO convertInfoToMapping(SubjectInfoBO subjectInfoBO);

}
