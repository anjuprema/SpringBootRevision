package com.anju.demo.component;

import org.springframework.stereotype.Component;

@Component
public class User {
	private Integer userId;
	private String userName;
	private Boolean isAdmin;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", isAdmin=" + isAdmin + "]";
	}	
}
