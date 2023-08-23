package com.demo.taskboard.dto;

import java.time.LocalDate;

public class TaskBoardUserResponseDto {

	private String taskId;
	private String assignorUserSapId;
	private String assigneeUserSapId;
	private String taskLastUpdatedBy;
	private LocalDate  taskLastUpdatedDate;
	private Boolean isActive;
	
	public String getAssignorUserSapId() {
		return assignorUserSapId;
	}
	public void setAssignorUserSapId(String assignorUserSapId) {
		this.assignorUserSapId = assignorUserSapId;
	}
	public String getAssigneeUserSapId() {
		return assigneeUserSapId;
	}
	public void setAssigneeUserSapId(String assigneeUserSapId) {
		this.assigneeUserSapId = assigneeUserSapId;
	}
	public String getTaskLastUpdatedBy() {
		return taskLastUpdatedBy;
	}
	public void setTaskLastUpdatedBy(String taskLastUpdatedBy) {
		this.taskLastUpdatedBy = taskLastUpdatedBy;
	}
	public LocalDate getTaskLastUpdatedDate() {
		return taskLastUpdatedDate;
	}
	public void setTaskLastUpdatedDate(LocalDate taskLastUpdatedDate) {
		this.taskLastUpdatedDate = taskLastUpdatedDate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
}
