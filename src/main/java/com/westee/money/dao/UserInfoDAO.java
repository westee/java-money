package com.westee.money.dao;

import com.westee.money.model.persistence.UserInfo;

public interface UserInfoDAO {

    UserInfo getUserInfoById(Long id);

    void createNewUser(String username, String password);
}
