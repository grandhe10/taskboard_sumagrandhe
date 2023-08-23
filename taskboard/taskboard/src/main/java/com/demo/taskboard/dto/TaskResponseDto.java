package com.demo.taskboard.dto;

import java.time.LocalDate;

public class TaskResponseDto {
	
	private String taskId;
	private String taskName;
	private String taskDescription;
	private LocalDate  taskCreatedDate;
	private Boolean isActive;
	private String  lastUpdatedBy;
	private LocalDate  lastUpdatedDate;
	private String taskCreatedBy;
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public LocalDate getTaskCreatedDate() {
		return taskCreatedDate;
	}
	public void setTaskCreatedDate(LocalDate taskCreatedDate) {
		this.taskCreatedDate = taskCreatedDate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public LocalDate getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public String getTaskCreatedBy() {
		return taskCreatedBy;
	}
	public void setTaskCreatedBy(String taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
