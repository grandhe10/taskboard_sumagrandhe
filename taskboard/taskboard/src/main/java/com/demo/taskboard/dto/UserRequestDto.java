package com.demo.taskboard.dto;

import javax.validation.constraints.NotEmpty;

public class UserRequestDto {
	
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getUin() {
		return uin;
	}
	public void setUin(String uin) {
		this.uin = uin;
	}
	@NotEmpty(message="userFirstName is mandatory field")
	private String userFirstName;
	@NotEmpty(message="userLastName is mandatory field")
	private String userLastName;
	private Boolean isActive;
	@NotEmpty(message="uin is mandatory field")
	private String uin;
	@NotEmpty(message="user created By is mandatory field")
	private String userCreatedBy;
	public String getUserCreatedBy() {
		return userCreatedBy;
	}
	public void setUserCreatedBy(String userCreatedBy) {
		this.userCreatedBy = userCreatedBy;
	}
	
	

	

}
