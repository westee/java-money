package com.westee.money.manager;

import com.westee.money.model.common.UserInfo;

public interface UserInfoManager {
    /**
     * 通过用户id获取用户信息
     * @param userId
     * @return UserInfo
     */
    UserInfo getUserInfoByUserId(Long userId);
}
