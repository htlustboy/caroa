package com.caroa.common;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.caroa.model.User;
import com.caroa.service.user.UserService;

public class JdbcRealm extends AuthorizingRealm{
	
	@Resource
	private UserService userService;
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//登录认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		User user = userService.queryUserByName(username);
		if(user==null){
			throw new UnknownAccountException("未找到该用户");
		}
		//user不为空，则获取该密码
		Object credentials = user.getPassword();
		//盐值
		ByteSource salt = ByteSource.Util.bytes(username);
		return new SimpleAuthenticationInfo(username, credentials, salt,getName());
	}

}
