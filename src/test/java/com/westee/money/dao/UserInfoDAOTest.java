package com.westee.money.dao;

import com.westee.money.dao.mapper.UserInfoMapper;
import com.westee.money.model.persistence.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserInfoDAOTest {
    @Mock
    private UserInfoMapper userInfoMapper;

    private UserInfoDAO userInfoDAO;

    @BeforeEach
    public void setup(){
        userInfoDAO = new UserInfoDAOImpl(userInfoMapper);
    }

    @Test
    public void testGetUserInfoById(){
        long userId = 1L;
        String username = "123456";
        String password = "123456";
        LocalDate now = LocalDate.now();

        UserInfo userInfo = UserInfo.builder().id(userId).username(username).password(password).createTime(now).build();
        doReturn(userInfo).when(userInfoMapper).getUserInfoByUserId(userId);

        UserInfo userInfoById = userInfoDAO.getUserInfoById(userId);
        assertEquals(userInfo, userInfoById);

        // userInfoMapper 的 getUserInfoByUserId被调用了一次。
        verify(userInfoMapper).getUserInfoByUserId(userId);
    }

    @Test
    public void testCreateNewUser(){


//        verify(userInfoMapper, never()).createNewUser();
    }
}
