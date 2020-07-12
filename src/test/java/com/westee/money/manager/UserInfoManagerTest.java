package com.westee.money.manager;

import com.westee.money.converter.persistence2common.UserInfoP2CConverter;
import com.westee.money.dao.UserInfoDAO;
import com.westee.money.exception.ResourceNotFoundException;
import com.westee.money.model.persistence.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class UserInfoManagerTest {

    @Mock
    private UserInfoDAO userInfoDAO;

    private UserInfoManager userInfoManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userInfoManager = new UserInfoManagerImpl(userInfoDAO, new UserInfoP2CConverter());
    }

    @Test
    public void testGetUserInfoByUserId() {
        // Arrange
        long userId = 1l;
        String username = "123456";
        String password = "123456";
        LocalDate now = LocalDate.now();

        UserInfo userInfo = UserInfo.builder().id(userId).username(username).password(password).createTime(now).build();

        doReturn(userInfo).when(userInfoDAO).getUserInfoById(userId);

        // Act
        com.westee.money.model.common.UserInfo userInfo1 = userInfoManager.getUserInfoByUserId(userId);

        // Assert
        assertThat(userInfo1).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);

//        assertEquals(userId, userInfo1.getId());
//        assertEquals("laowang", userInfo1.getUsername());
//        assertEquals("laowang", userInfo1.getPassword());

        verify(userInfoDAO).getUserInfoById(ArgumentMatchers.eq(userId) );
    }

    @Test
    public void testGetUserInfoByUserIdWithInvalidUserId() {
        long userId = -1;
        doReturn(null).when(userInfoDAO).getUserInfoById(userId);

        assertThrows(ResourceNotFoundException.class, () -> userInfoManager.getUserInfoByUserId(userId));
        verify(userInfoDAO).getUserInfoById(ArgumentMatchers.eq(userId) );
    }
}
