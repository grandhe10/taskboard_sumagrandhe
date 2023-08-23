package com.demo.taskboard.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.taskboard.dto.TaskBoardRequestDto;
import com.demo.taskboard.dto.TaskBoardUserResponseDto;
import com.demo.taskboard.service.TaskBoardService;

@RestController
public class TaskBoardController {
	
	Log logger = LogFactory.getLog(TaskBoardController.class);
	
	@Autowired
	TaskBoardService taskBoardService;
	
	@PostMapping("/taskboards")
	public ResponseEntity<String> addTasksToTaskBoard(@Valid TaskBoardRequestDto taskBoardRequestDto)
	{
		logger.info("add tasks to taskboard by user is invocated ");
		taskBoardService.saveTaskBoardDetails(taskBoardRequestDto);
		return new ResponseEntity<String>("Requested task is assigned to user and added to task board",HttpStatus.CREATED);
	}
	
	@GetMapping("/users/{userSapId}/taskboards")
	public ResponseEntity<List<TaskBoardUserResponseDto>> getTaskBoardDetailsByUserId(@PathVariable String userSapId)
	{
		logger.info("Getting all tasks assigned to requested user or list of assignor tasks");
		taskBoardService.getTaskBoardDetailsByUserSapId(userSapId);
		return new ResponseEntity<List<TaskBoardUserResponseDto>>(taskBoardService.getTaskBoardDetailsByUserSapId(userSapId),HttpStatus.OK);
	}
	
	/*
	 * @GetMapping("/taskboards") public
	 * ResponseEntity<List<TaskBoardUserResponseDto>> getAllTasks(@Valid
	 * TaskBoardRequestDto taskBoardRequestDto,@RequestParam (value = "userSapId")
	 * String userSapId) { logger.info("To get all task details from taskboard");
	 * return new ResponseEntity<List<TaskBoardUserResponseDto>>(taskBoardService.
	 * getAllTaskBoardDetails(userSapId),HttpStatus.OK); }
	 */
	
	@PostMapping("users/{userSapId}/taskboards/{date}")
	public ResponseEntity<List<TaskBoardUserResponseDto>> getTasksOnSpecificDate(@PathVariable String userSapId,@PathVariable String date)
	{
		logger.info("To get all task details from taskboard");	
		return new ResponseEntity<List<TaskBoardUserResponseDto>>(taskBoardService.getTaskBoardDetailsByAssignorUserIdAndSpecificDate(userSapId,date),HttpStatus.OK);
	}
	
	@PutMapping("users/{userSapId}/taskboards/{taskId}")
	public ResponseEntity<String> updateTaskStatus(@PathVariable String userSapId,@PathVariable String taskId)
	{
		return new ResponseEntity<>("Task status updated successfully",HttpStatus.ACCEPTED);
		
	}
	
	
	
	
	
	
}