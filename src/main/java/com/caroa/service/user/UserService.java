package com.caroa.service.user;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caroa.common.TaskStatus;
import com.caroa.dao.UserMapper;
import com.caroa.model.User;
import com.caroa.util.BaseUtil;

@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Resource
	UserMapper userMapper;
	
	public TaskStatus doLogin(String username,String password,String remember){
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			if (BaseUtil.isNotBlank(remember)) {
				token.setRememberMe(true);
			}
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				logger.warn(uae.getMessage());
				return TaskStatus.WARNING("未找到该用户");
			}catch (IncorrectCredentialsException ice) {
				logger.warn(ice.getMessage());
				return TaskStatus.WARNING("密码错误");
			} catch (LockedAccountException lae) {
				logger.warn(lae.getMessage());
				return TaskStatus.WARNING("账号被在锁定");
			} 
		}
		return TaskStatus.SUCCESS();
	}
	
	//根据用户名查找用户
	public User queryUserByName(String username){
		return userMapper.queryUserByName(username);
	}
}