package com.westee.money.controller;

import static org.mockito.ArgumentMatchers.anyLong;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.westee.money.converter.common2service.UserInfoC2SConverter;
import com.westee.money.exception.GlobalExceptionHandler;
import com.westee.money.manager.UserInfoManager;
import com.westee.money.model.common.UserInfo;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserInfoManager userInfoManager;

    @Mock
    private UserInfoC2SConverter userInfoC2SConverter;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @AfterEach
    void clear() {
        reset(userInfoManager);
        reset(userInfoC2SConverter);
    }

    @Test
    void getUserInfoByUserIdTest() throws Exception {
        // Arrange
        val userId = 100L;
        val username = "hardcore";
        val password = "hardcore";

        val userInfoInCommon = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .build();

        val userInfo = com.westee.money.model.service.UserInfo
                .builder()
                .id(userId)
                .username(username)
                .password(password)
                .build();

        doReturn(userInfoInCommon).when(userInfoManager).getUserInfoByUserId(anyLong());

        doReturn(userInfo).when(userInfoC2SConverter).convert(userInfoInCommon);

        // Act && Assert
        mockMvc.perform(get("/v1.0/users/" + userId).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(
//                        "{\"id\":100,\"username\":\"hardcore\",\"password\":\"hardcore\"}"
                        new ObjectMapper().writeValueAsString(userInfo)
                ));

        verify(userInfoManager).getUserInfoByUserId(anyLong());
        verify(userInfoC2SConverter).convert(userInfoInCommon);
    }

    @Test
    public void getUserInfoByUserIdTestWithInvalidParam() throws Exception {
        val userId = -1L;
//        doThrow(new ResourceNotFoundException(String.format("User %s was not found", userId)))
//                .when(userInfoManager)
//                .getUserInfoByUserId(anyLong());

        mockMvc.perform(get("/v1.0/users/" + userId)
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(
                        "{\"code\":\"INVALID_PARAMETER\",\"errorType\":\"Client\",\"message\":\"User -1 was not found\",\"statusCode\":400}"
                        ));

        verify(userInfoManager, never()).getUserInfoByUserId(anyLong());
    }
}
