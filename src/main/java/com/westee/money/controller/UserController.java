package com.westee.money.controller;

import com.westee.money.converter.common2service.UserInfoC2SConverter;
import com.westee.money.exception.InvalidParameterException;
import com.westee.money.manager.UserInfoManager;
import com.westee.money.model.service.UserInfo;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
@Slf4j
public class UserController {
    private final UserInfoManager userInfoManager;
    private final UserInfoC2SConverter userInfoConverter;

    @Autowired
    public UserController(UserInfoManager userInfoManager, UserInfoC2SConverter userInfoConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoConverter = userInfoConverter;
    }

    @GetMapping("/{id}")
    public UserInfo getUserInfoByUserId(@PathVariable("id") Long userId){
        log.debug("Get user info by user id {}", userId);
        if(userId  == null || userId <= 0L){
            throw new InvalidParameterException(String.format("The user id %s is invalid", userId));
        }
        val userInfo = userInfoManager.getUserInfoByUserId(userId);
        return userInfoConverter.convert(userInfo);
    }
}
