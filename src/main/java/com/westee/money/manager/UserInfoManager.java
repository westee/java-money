package com.westee.money.manager;

import com.westee.money.model.common.UserInfo;

public interface UserInfoManager {
    /**
     * 通过用户id获取用户信息
     * @param userId
     * @return UserInfo
     */
    UserInfo getUserInfoByUserId(Long userId);

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return UserInfo
     */
    UserInfo getUserInfoByUsername(String username);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    UserInfo login(String username, String password);

    /**
     * 注册用户
     * @param username
     * @param password
     * @return
     */
    UserInfo createUser(String username, String password);
}
