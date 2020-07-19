package com.westee.money.config;

import com.westee.money.manager.UserInfoManager;
import com.westee.money.model.common.UserInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRealm extends AuthorizingRealm {
    private final UserInfoManager userInfoManager;

    @Autowired
    public UserRealm(UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        UserInfo userInfoByUsername = userInfoManager.getUserInfoByUsername(username);

        if (!password.equals(userInfoByUsername.getPassword())) {
            throw new UnknownAccountException(String.format("the password is invalid for %s", username));
        }
        return new SimpleAuthenticationInfo(userInfoByUsername.getUsername(),
                userInfoByUsername.getPassword(), this.getName());
    }
}
