package com.westee.money.converter.common2service;

import com.google.common.base.Converter;
import com.westee.money.model.common.UserInfo;

public class UserInfoC2SConverter extends Converter<UserInfo, com.westee.money.model.service.UserInfo> {
    @Override
    protected com.westee.money.model.service.UserInfo doForward(UserInfo userInfo) {
        return com.westee.money.model.service.UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.westee.money.model.service.UserInfo userInfo) {
        return UserInfo.builder()
                .id(userInfo.getId())
                .password(userInfo.getPassword())
                .username(userInfo.getUsername())
                .build();
    }
}
