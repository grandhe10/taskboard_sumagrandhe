package com.demo.taskboard.service;

import com.demo.taskboard.dto.TaskRequestDto;
import com.demo.taskboard.dto.TaskResponseDto;

public interface TaskService {
	
	/**
	 * This method is used to save tasks
	 * @param taskDto
	 * @return TaskResponseDto with task details saved 
	 */
	TaskResponseDto  saveTaskDetails(TaskRequestDto taskDto);
	
	

}
