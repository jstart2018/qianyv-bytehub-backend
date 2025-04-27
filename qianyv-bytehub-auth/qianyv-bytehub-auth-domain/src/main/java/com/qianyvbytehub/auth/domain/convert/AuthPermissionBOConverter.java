package com.qianyvbytehub.auth.domain.convert;


import com.qianyvbytehub.auth.domain.entity.AuthPermissionBO;
import com.qianyvbytehub.auth.infra.basic.entity.AuthPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthPermissionBOConverter {

    AuthPermissionBOConverter INSTANCE = Mappers.getMapper(AuthPermissionBOConverter.class);

    AuthPermission ConvertPermissionBoToEntity(AuthPermissionBO authPermissionBO);


}
