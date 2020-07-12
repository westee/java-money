package com.westee.money.converter;

import com.westee.money.converter.persistence2common.UserInfoP2CConverter;
import com.westee.money.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class UserInfoP2CConverterTest {
    private final UserInfoP2CConverter  userInfoP2CConverter= new UserInfoP2CConverter();

    @Test
    public void testForward(){
        long userId = 1L;
        String username = "123456";
        String password = "123456";
        LocalDate now = LocalDate.now();

        UserInfo userInfo = UserInfo.builder().id(userId).username(username).password(password).createTime(now).build();

        com.westee.money.model.common.UserInfo userInfo1 = userInfoP2CConverter.doForward(userInfo);
        assertThat(userInfo1).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);

    }

    @Test
    public void testBackForward(){
        long userId = 1L;
        String username = "123456";
        String password = "123456";

        val userInfo =  com.westee.money.model.common.UserInfo.builder().id(userId).username(username).password(password).build();

        val commonUserInfo = userInfoP2CConverter.doBackward(userInfo);


        assertThat(commonUserInfo).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);

    }
}
