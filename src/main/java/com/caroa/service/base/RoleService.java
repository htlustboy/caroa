package com.caroa.service.base;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.caroa.dao.RoleMapper;
import com.caroa.model.Role;

@Service
public class RoleService {
	
	@Resource
	private RoleMapper roleMapper;
	
	//根据用户id获取该用户对应的角色名称
	public Set<String> getRoleNameByUserId(String userId){
		return roleMapper.getRoleNameByUserId(userId);
	}
	
	//根据用户id获取所有的角色
	public List<Role> getRoleListByUserId(String userId){
		return roleMapper.getRoleListByUserId(userId);
	}
	
}
