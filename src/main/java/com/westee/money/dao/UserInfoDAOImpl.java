package com.westee.money.dao;

import com.westee.money.dao.mapper.UserInfoMapper;
import com.westee.money.model.persistence.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoDAOImpl implements UserInfoDAO {
    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoDAOImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserInfo getUserInfoById(Long id) {
        return userInfoMapper.getUserInfoByUserId(id);
    }

    @Override
    public UserInfo getUserInfoByUsername(String username) {
        return userInfoMapper.getUserInfoByUsername(username);
    }

    @Override
    public UserInfo createNewUser(String username, String password) {
        return null;
    }
}
