package com.qianyvbytehub.auth.domain.convert;


import com.qianyvbytehub.auth.domain.entity.AuthUserRoleBO;
import com.qianyvbytehub.auth.infra.basic.entity.AuthUserRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserRoleBOConverter {

    AuthUserRoleBOConverter INSTANCE = Mappers.getMapper(AuthUserRoleBOConverter.class);

    AuthUserRole ConvertUserRoleBoToEntity(AuthUserRoleBO authUserRoleBO);


}
