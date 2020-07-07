package com.westee.money.manager;

import com.westee.money.converter.persistence2common.UserInfoP2CConverter;
import com.westee.money.dao.UserInfoDAO;
import com.westee.money.exception.ResourceNotFoundException;
import com.westee.money.model.common.UserInfo;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoManagerImpl implements UserInfoManager {
    private final UserInfoDAO userInfoDAO;
    private final UserInfoP2CConverter userInfoP2CConverter;

    public UserInfoManagerImpl(UserInfoDAO userInfoDAO, UserInfoP2CConverter userInfoP2CConverter) {
        this.userInfoDAO = userInfoDAO;
        this.userInfoP2CConverter = userInfoP2CConverter;
    }

    @Override
    public  UserInfo getUserInfoByUserId(Long userId) {
        val userInfo = Optional.ofNullable(userInfoDAO.getUserInfoById(userId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("There is no user %s", userId)));
        return userInfoP2CConverter.convert(userInfo);
    }
}
