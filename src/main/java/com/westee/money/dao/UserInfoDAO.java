package com.westee.money.dao;

import com.westee.money.model.persistence.UserInfo;

public interface UserInfoDAO {

    UserInfo getUserInfoById(Long id);

    UserInfo getUserInfoByUsername(String username);

    void createNewUser(UserInfo userInfo);
}
