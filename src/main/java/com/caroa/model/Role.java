package com.caroa.model;

import org.springframework.boot.orm.jpa.EntityScan;

@EntityScan
public class Role {
	
	private String id;
	private String roleName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
