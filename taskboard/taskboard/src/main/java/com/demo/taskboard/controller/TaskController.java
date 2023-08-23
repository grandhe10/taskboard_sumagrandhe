package com.demo.taskboard.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.taskboard.dto.TaskRequestDto;
import com.demo.taskboard.dto.TaskResponseDto;
import com.demo.taskboard.service.TaskService;

@RestController
public class TaskController {
	
	Log logger = LogFactory.getLog(TaskController.class);
	
	@Autowired
	TaskService taskService;
	
	@PostMapping("/tasks")
	public ResponseEntity<TaskResponseDto> createTasks(@Valid TaskRequestDto taskDto)
	{
		logger.info("task create request invoked");
		return new ResponseEntity<TaskResponseDto>(taskService.saveTaskDetails(taskDto),HttpStatus.CREATED);
		
	}
	
	
			

}
