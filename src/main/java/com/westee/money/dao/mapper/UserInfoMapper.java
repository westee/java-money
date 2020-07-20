package com.westee.money.dao.mapper;

import com.westee.money.model.persistence.UserInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserInfoMapper {
    @Select("SELECT id, username, password, create_time, update_time FROM hcas_userinfo WHERE id = #{id}")
    UserInfo getUserInfoByUserId(@Param("id") Long id);

    @Select("SELECT id, username, password, salt, create_time, update_time FROM hcas_userinfo WHERE username = #{username}")
    UserInfo getUserInfoByUsername(@Param("username") String username);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO hcas_userinfo(username, password, salt, create_time) " +
            "VALUES (#{username}, #{password}, #{salt}, #{createTime})")
    int createNewUser(UserInfo userInfo);

}
