package com.demo.taskboard.service;

import java.util.List;

import com.demo.taskboard.dto.TaskBoardRequestDto;
import com.demo.taskboard.dto.TaskBoardUserResponseDto;

public interface TaskBoardService {
	/**
	 * This method is used to save tasks to taskboard
	 * 
	 */
	void saveTaskBoardDetails(TaskBoardRequestDto taskBoardRequestDto);

	/**
	 * This method is used to get list of tasks using userSapId
	 * @param userSapId
	 * @return List<TaskBoardUserResponseDto> list of task details from taskboard
	 */
	List<TaskBoardUserResponseDto> getTaskBoardDetailsByUserSapId(String userSapId);

	/**
	 * This method is used to get list of all tasks from taskboard
	 * @param userSapId
	 * @return List<TaskBoardUserResponseDto> list of task details from taskboard
	 */
	List<TaskBoardUserResponseDto> getAllTaskBoardDetails(String userSapId);

	/**
	 * This method is used to get list of all tasks from taskboard for a specific date
	 * @param userSapId
	 * @param date
	 * @return List<TaskBoardUserResponseDto> list of task details from taskboard
	 */
	
	List<TaskBoardUserResponseDto> getTaskBoardDetailsByAssignorUserIdAndSpecificDate(String userSapId, String date);
	
	/**
	 * This method is used to get updateTaskStatus 
	 * @param userSapId
	 * @param taskId
	 */

	void updateTaskBoardByAssigneeAndUserSapId(String taskId, String userSapId);

}
