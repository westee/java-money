package com.westee.money.manager;

import com.westee.money.converter.persistence2common.UserInfoP2CConverter;
import com.westee.money.dao.UserInfoDAO;
import com.westee.money.exception.InvalidParameterException;
import com.westee.money.exception.ResourceNotFoundException;
import com.westee.money.model.common.UserInfo;
import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserInfoManagerImpl implements UserInfoManager {

    public static final int HASH_ITERATIONS = 2;
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

    @Override
    public UserInfo getUserInfoByUsername(String username) {
        val userInfo = Optional.ofNullable(userInfoDAO.getUserInfoByUsername(username))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("There is no user %s", username)));
        return userInfoP2CConverter.convert(userInfo);
    }

    @Override
    public UserInfo login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        subject.login(usernamePasswordToken);
        return null;
    }

    @Override
    public UserInfo createUser(String username, String password) {
        // 用户名是否已经注册
        com.westee.money.model.persistence.UserInfo userInfoByUsername = userInfoDAO.getUserInfoByUsername(username);
        if(userInfoByUsername != null){
            throw new InvalidParameterException(String.format("the username %s already exists", username));
        }

        String salt = UUID.randomUUID().toString();
        String encryptedPassword = new Sha256Hash(password, salt, HASH_ITERATIONS).toBase64();

        com.westee.money.model.persistence.UserInfo userInfo = com.westee.money.model.persistence.UserInfo.builder()
                .username(username)
                .password(encryptedPassword)
                .salt(salt)
                .createTime(LocalDate.now())
                .build();

        userInfoDAO.createNewUser(userInfo);

        return userInfoP2CConverter.doForward(userInfo);
    }

}
