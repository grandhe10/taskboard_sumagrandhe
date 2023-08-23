package com.demo.taskboard.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

public class TaskRequestDto {
	@NotEmpty(message="taskName is mandatory field")
	private String taskName;
	@NotEmpty(message = "taskCreatedBy cannot be empty")
	String taskCreatedBy;
	private String taskDescription;
	private LocalDate  taskCreatedDate;
	private Boolean isActive;
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
	
	public String getTaskCreatedBy() {
		return taskCreatedBy;
	}

	public void setTaskCreatedBy(String taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public LocalDate getTaskCreatedDate() {
		return taskCreatedDate;
	}
	public void setTaskCreatedDate(LocalDate taskCreatedDate) {
		this.taskCreatedDate = taskCreatedDate;
	}

}
