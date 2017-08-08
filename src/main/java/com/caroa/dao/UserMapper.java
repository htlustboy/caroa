package com.caroa.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.caroa.model.User;


@Component
public interface UserMapper {
	
	User queryUserByName(@Param("username")String username);
	
	void saveUser(User user);
}
