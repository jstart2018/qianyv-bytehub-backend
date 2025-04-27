package com.qianyvbytehub.auth.application.controller.convert;


import com.qianyvbytehub.auth.application.controller.entity.AuthPermissionDTO;
import com.qianyvbytehub.auth.domain.entity.AuthPermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthPermissionDTOConverter {

    AuthPermissionDTOConverter INSTANCE = Mappers.getMapper(AuthPermissionDTOConverter.class);


    AuthPermissionBO ConvertPermissionDtoToBo(AuthPermissionDTO authPermissionDTO);



}
