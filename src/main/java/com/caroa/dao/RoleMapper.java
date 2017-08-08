package com.caroa.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.caroa.model.Role;


@Component
public interface RoleMapper {
		
	Set<String> getRoleNameByUserId(@Param("userId")String userId);
	
	List<Role> getRoleListByUserId(@Param("userId")String userId);
}
