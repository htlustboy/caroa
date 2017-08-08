package com.caroa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface PermissionMapper {
	
	List<String> getPermissionNameByRid(@Param("roleId")String roleId);
}
