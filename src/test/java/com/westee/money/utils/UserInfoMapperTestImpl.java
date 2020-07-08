package com.westee.money.utils;

import com.westee.money.dao.mapper.UserInfoMapper;
import com.westee.money.model.persistence.UserInfo;

import java.time.LocalDate;

public class UserInfoMapperTestImpl implements UserInfoMapper {
    @Override
    public UserInfo getUserInfoByUserId(Long id) {
        return id > 0 ?
                UserInfo.builder().id(id).username("laowang").password("laowang").createTime(LocalDate.now()).build()
                : null;
    }
}
