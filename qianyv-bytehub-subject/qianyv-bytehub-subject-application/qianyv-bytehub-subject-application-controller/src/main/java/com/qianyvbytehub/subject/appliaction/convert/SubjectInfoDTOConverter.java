package com.qianyvbytehub.subject.appliaction.convert;


import com.qianyvbytehub.subject.appliaction.entity.SubjectInfoDTO;
import com.qianyvbytehub.subject.common.entity.PageResult;
import com.qianyvbytehub.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 题目信息dto转换器
 *
 * @author: ChickenWing
 * @date: 2023/10/8
 */
@Mapper
public interface SubjectInfoDTOConverter {

    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDTOToBO(SubjectInfoDTO subjectInfoDTO);

    SubjectInfoDTO convertBOToDTO(SubjectInfoBO subjectInfoBO);

    PageResult<SubjectInfoDTO> convertPageBOListToDTO(PageResult<SubjectInfoBO> subjectInfoPageBOList);

}
