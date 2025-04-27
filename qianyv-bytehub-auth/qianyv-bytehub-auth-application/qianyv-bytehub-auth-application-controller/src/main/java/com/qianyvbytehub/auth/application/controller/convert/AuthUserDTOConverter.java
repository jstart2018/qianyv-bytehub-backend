package com.qianyvbytehub.auth.application.controller.convert;


import com.qianyvbytehub.auth.application.controller.entity.AuthUserDTO;
import com.qianyvbytehub.auth.domain.entity.AuthUserBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserDTOConverter {

    AuthUserDTOConverter INSTANCE = Mappers.getMapper(AuthUserDTOConverter.class);


    AuthUserBo ConvertUserDtoToBo(AuthUserDTO authUserDTO);

    AuthUserDTO ConvertUserBoToDto(AuthUserBo authUserBo);



}
