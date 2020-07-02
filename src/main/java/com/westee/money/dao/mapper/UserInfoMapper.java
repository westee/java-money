package com.westee.money.dao.mapper;

import com.westee.money.model.persistence.UserInfo;

@Mapper
public class UserInfoMapper {
    @Select("SELECT id, username, password, create_time, update_time FROM hcas_userinfo WHERE id = #{id}")
    UserInfo getUserInfoByUserId(@Param("id") Long id);
}
