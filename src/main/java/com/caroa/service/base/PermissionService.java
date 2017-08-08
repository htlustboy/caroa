package com.caroa.service.base;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.caroa.dao.PermissionMapper;

@Service
public class PermissionService {
	
	@Resource
	private PermissionMapper permissionMapper;
	
	//根据roleid查找所有的权限名称
	public List<String> getPermissionNameByRid(String roleId){
		return permissionMapper.getPermissionNameByRid(roleId);
	}
}
