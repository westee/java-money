package com.westee.money.converter.persistence2common;

import com.google.common.base.Converter;
import com.westee.money.model.persistence.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoP2CConverter extends Converter<UserInfo, com.westee.money.model.common.UserInfo> {
    @Override
    protected com.westee.money.model.common.UserInfo doForward(UserInfo userInfo) {
        return com.westee.money.model.common.UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.westee.money.model.common.UserInfo userInfo) {
        return UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }
}
