package com.westee.money.controller;

import com.westee.money.converter.common2service.UserInfoC2SConverter;
import com.westee.money.exception.InvalidParameterException;
import com.westee.money.manager.UserInfoManager;
import com.westee.money.model.service.UserInfo;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("v1.0/users")
@Slf4j
public class UserController {
    private final UserInfoManager userInfoManager;
    private final UserInfoC2SConverter userInfoC2SConverter;

    @Autowired
    public UserController(UserInfoManager userInfoManager, UserInfoC2SConverter userInfoConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoC2SConverter = userInfoConverter;
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getUserInfoByUserId(@PathVariable("id") Long userId){
        log.debug("Get user info by user id {}", userId);
        if(userId  == null || userId <= 0L){
            throw new InvalidParameterException(String.format("User %s was not found", userId));
        }
        val userInfo = userInfoManager.getUserInfoByUserId(userId);
        return ResponseEntity.ok(Objects.requireNonNull(userInfoC2SConverter.convert(userInfo)));
    }
}
