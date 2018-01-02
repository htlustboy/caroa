package com.caroa.model.export;

import com.caroa.annotcaion.Export;

public class TTVo {
	
	@Export(index=0,name="ID",type="id")
	private String id;
	
	@Export(index=1,name="用户名",type="username")
	private String username;
	
	@Export(index=2,name="密码",type="password")
	private String password;
	
	@Export(index=3,name="日期",type="date")
	private String date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
