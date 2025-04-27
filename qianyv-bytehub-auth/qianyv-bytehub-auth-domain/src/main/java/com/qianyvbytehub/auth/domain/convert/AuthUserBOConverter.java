package com.qianyvbytehub.auth.domain.convert;


import com.qianyvbytehub.auth.domain.entity.AuthUserBo;
import com.qianyvbytehub.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserBOConverter {

    AuthUserBOConverter INSTANCE = Mappers.getMapper(AuthUserBOConverter.class);

    AuthUser ConvertBoToEntity(AuthUserBo authUserBo);

    AuthUserBo ConvertEntityToBo(AuthUser authUser);


}
