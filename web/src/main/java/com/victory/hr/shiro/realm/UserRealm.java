package com.victory.hr.shiro.realm;

import com.victory.hr.sys.entity.User;
import com.victory.hr.sys.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户的权限Realm
 *
 * @author ajkx_Du
 * @create 2016-11-03 21:02
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //分配权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }

    //用户认证(即登录验证)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取输入的账号
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.findByUserName(username);
        //没找到账号
        if (user == null) {
            throw new UnknownAccountException();
        }

        //账号锁定
        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException();
        }

        //使用AuthenticationRealm其CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getName(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName()
        );
        return authenticationInfo;
    }
}
