package com.qianyvbytehub.auth.application.controller.convert;


import com.qianyvbytehub.auth.application.controller.entity.AuthRoleDTO;
import com.qianyvbytehub.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRoleDTOConverter {

    AuthRoleDTOConverter INSTANCE = Mappers.getMapper(AuthRoleDTOConverter.class);


    AuthRoleBO ConvertRoleDtoToBo(AuthRoleDTO authRoleDTO);



}
