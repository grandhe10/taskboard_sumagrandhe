package com.demo.taskboard.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskBoard {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
	private Long taskBoardId;
	private String taskId;
	private String assignorUserSapId;
	private String assigneeUserSapId;
	private String taskLastUpdatedBy;
	private LocalDate  taskLastUpdatedDate;
	private Boolean isActive;
	
	
	public Long getTaskBoardId() {
		return taskBoardId;
	}
	public void setTaskBoardId(Long taskBoardId) {
		this.taskBoardId = taskBoardId;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	
	

}
