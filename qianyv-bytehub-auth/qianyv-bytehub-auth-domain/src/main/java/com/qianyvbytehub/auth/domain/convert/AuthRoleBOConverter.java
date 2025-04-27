package com.qianyvbytehub.auth.domain.convert;


import com.qianyvbytehub.auth.domain.entity.AuthRoleBO;
import com.qianyvbytehub.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRoleBOConverter {

    AuthRoleBOConverter INSTANCE = Mappers.getMapper(AuthRoleBOConverter.class);

    AuthRole ConvertRoleBoToEntity(AuthRoleBO authRoleBO);


}
