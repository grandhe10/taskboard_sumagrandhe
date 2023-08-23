package com.demo.taskboard.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TaskDto {
	
	@NotNull
	TaskRequestDto taskRequestDto;
	@NotEmpty(message = "taskCreatedBy cannot be empty")
	String taskCreatedBy;

	
	public String getTaskCreatedBy() {
		return taskCreatedBy;
	}

	public void setTaskCreatedBy(String taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}

}
