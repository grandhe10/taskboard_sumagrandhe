package com.demo.taskboard.dto;

import javax.validation.constraints.NotEmpty;

public class TaskBoardRequestDto {
	@NotEmpty(message="assignee is mandatory field")
	private String assigneeUserSapId;
	private Boolean isActive;
	@NotEmpty(message="taskId is mandatory field")
	private String taskId;
	private String assignorSapId;
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	public String getAssigneeUserSapId() {
		return assigneeUserSapId;
	}
	public void setAssigneeUserSapId(String assigneeUserSapId) {
		this.assigneeUserSapId = assigneeUserSapId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getAssignorSapId() {
		return assignorSapId;
	}
	public void setAssignorSapId(String assignorSapId) {
		this.assignorSapId = assignorSapId;
	}
	
	
}
