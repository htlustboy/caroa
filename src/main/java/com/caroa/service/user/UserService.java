package com.caroa.service.user;

import java.util.UUID;

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
		currentUser.logout();
		if(!currentUser.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			if (BaseUtil.isNotBlank(remember)) {
				token.setRememberMe(true);
			}else{
				token.setRememberMe(false);
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
	
	//注册用户
	public void save(String username,String password,String displayName,String email,String address,String phoneNum,String memo){
		User user = new User();
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		user.setId(id);
		user.setUsername(username);
		user.setPassword(BaseUtil.password2Hex(username, password));
		user.setDisplayName(displayName);
		user.setEmail(email);
		user.setPhoneNum(phoneNum);
		user.setAddress(address);
		user.setMemo(memo);
		userMapper.saveUser(user);
	}
	
	//更新
	public void updateUserCode(String username,String code){
		userMapper.updateUserCode(username,code);
	}
	
	//清除激活码,定时任务
	public void clearCode(){
		userMapper.clearCode();
	}
	
	//修改密码
	public Boolean updatePwd(String username,String password,String code){
		int result = userMapper.updatePwd(username,password,code);
		return result==1?true:false;
	}
}
