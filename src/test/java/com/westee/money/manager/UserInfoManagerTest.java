package com.westee.money.manager;

import com.westee.money.converter.persistence2common.UserInfoP2CConverter;
import com.westee.money.dao.UserInfoDAO;
import com.westee.money.dao.UserInfoDAOImpl;
import com.westee.money.dao.mapper.UserInfoMapper;
import com.westee.money.exception.ResourceNotFoundException;
import com.westee.money.model.common.UserInfo;
import com.westee.money.utils.UserInfoMapperTestImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserInfoManagerTest {

    private UserInfoManager userInfoManager;
    @BeforeEach
    public void setUp(){
        UserInfoMapper userInfoMapper = new UserInfoMapperTestImpl();
        UserInfoP2CConverter userInfoP2CConverter = new UserInfoP2CConverter();
        UserInfoDAO userInfoDAO = new UserInfoDAOImpl(userInfoMapper);
        userInfoManager = new UserInfoManagerImpl(userInfoDAO, userInfoP2CConverter);
    }

    @Test
    public void testGetUserInfoByUserId(){
        long userId = 1l;

        UserInfo userInfo = userInfoManager.getUserInfoByUserId(userId);

        assertEquals(userId, userInfo.getId());
        assertEquals("laowang", userInfo.getUsername());
        assertEquals("laowang", userInfo.getPassword());
    }

    @Test
    public void testGetUserInfoByUserIdWithInvalidUserId(){
        long userId = -1;

//        UserInfo userInfo = userInfoManager.getUserInfoByUserId(userId);
        assertThrows(ResourceNotFoundException.class, ()-> userInfoManager.getUserInfoByUserId(userId));
    }
}
