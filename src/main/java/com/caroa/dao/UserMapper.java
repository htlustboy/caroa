package com.caroa.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.caroa.model.User;


@Component
public interface UserMapper {
	
	User queryUserByName(@Param("username")String username);
	
	void saveUser(User user);
	
	void updateUserCode(@Param("username")String username,@Param("code")String code);
	
	void clearCode();
	
	int updatePwd(@Param("username")String username,@Param("password")String password,@Param("code")String code);
}
