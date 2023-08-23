package com.demo.taskboard.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demo.taskboard.constants.ApplicationConstants;
import com.demo.taskboard.dao.TaskBoardRepository;
import com.demo.taskboard.dao.TaskRepository;
import com.demo.taskboard.dao.UserRepository;
import com.demo.taskboard.dto.TaskBoardRequestDto;
import com.demo.taskboard.dto.TaskBoardUserResponseDto;
import com.demo.taskboard.exception.ResourceNotFoundException;
import com.demo.taskboard.model.Task;
import com.demo.taskboard.model.TaskBoard;
import com.demo.taskboard.model.User;
import com.demo.taskboard.service.TaskBoardService;

@Service
public class TaskBoardServiceImpl implements TaskBoardService {

	Log logger = LogFactory.getLog(TaskBoardServiceImpl.class);

	@Autowired
	TaskBoardRepository taskBoardRepository;

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public void saveTaskBoardDetails(TaskBoardRequestDto taskBoardRequestDto) {
		Optional<Task> taskOptional = taskRepository.findByTaskIdAndIsActive(taskBoardRequestDto.getTaskId(),
				Boolean.TRUE);
		if (!taskOptional.isPresent())
			throw new ResourceNotFoundException("Selected task is invalid or Inactive", HttpStatus.NOT_FOUND);
		Optional<User> userOptional = userRepository.findByUserSapIdAndRoles(taskBoardRequestDto.getAssignorSapId(),
				ApplicationConstants.ROLE_MANAGER);
		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("User Details are incorrect or User does not have enough previleges",
					HttpStatus.BAD_REQUEST);
		Optional<User> userOptional1 = userRepository.findByUserSapIdAndIsActive(taskBoardRequestDto.getAssignorSapId(),
				Boolean.TRUE);
		if (!userOptional1.isPresent())
			throw new ResourceNotFoundException("User is inactive", HttpStatus.EXPECTATION_FAILED);
		TaskBoard taskBoard = new TaskBoard();
		BeanUtils.copyProperties(taskBoardRequestDto, taskBoard);
		taskBoard.setAssignorUserSapId(taskBoardRequestDto.getAssignorSapId());
		taskBoard.setAssigneeUserSapId(taskBoardRequestDto.getAssigneeUserSapId());
		taskBoard.setIsActive(Boolean.TRUE);
		taskBoard.setTaskLastUpdatedBy(taskBoardRequestDto.getAssignorSapId());
		taskBoard.setTaskLastUpdatedDate(LocalDate.now());
		taskBoardRepository.save(taskBoard);
	}

	@Override
	public List<TaskBoardUserResponseDto> getTaskBoardDetailsByUserSapId(String userSapId) {
		Optional<User> userOptional = userRepository.findByUserSapId(userSapId);
		Optional<List<TaskBoard>> taskBoardList = Optional.empty();
		if (userOptional.isEmpty())
			throw new ResourceNotFoundException("Please verify requested user details", HttpStatus.NOT_FOUND);
		logger.info("user valid");
		if (userOptional.get().getRoles().equals("ROLE_MANAGER")) {
			taskBoardList = taskBoardRepository.findByAssignorUserSapId(userSapId);
			if (taskBoardList.isEmpty())
				throw new ResourceNotFoundException("No tasks found ", HttpStatus.NOT_FOUND);
		} else {
			taskBoardList = taskBoardRepository.findByAssigneeUserSapId(userSapId);
			if (taskBoardList.isEmpty())
				throw new ResourceNotFoundException("No tasks found ", HttpStatus.NOT_FOUND);
		}

		List<TaskBoardUserResponseDto> taskBoardDtoList = taskBoardList.get().stream()
				.map(taskBoard -> getTaskBoardDto(taskBoard)).collect(Collectors.toList());
		return taskBoardDtoList;
	}

	private TaskBoardUserResponseDto getTaskBoardDto(TaskBoard taskBoard) {
		TaskBoardUserResponseDto taskBoardUserResponseDto = new TaskBoardUserResponseDto();
		BeanUtils.copyProperties(taskBoard, taskBoardUserResponseDto);
		taskBoardUserResponseDto.setTaskId(null);
		return taskBoardUserResponseDto;
	}

	@Override
	public List<TaskBoardUserResponseDto> getAllTaskBoardDetails(String userSapId) {
		Optional<User> userOptional = userRepository.findByUserSapIdAndRoles(userSapId,
				ApplicationConstants.ROLE_MANAGER);
		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("UserSapId is incorrect or user is previliged to perform this action",
					HttpStatus.BAD_REQUEST);
		List<TaskBoard> taskBoardList = taskBoardRepository.findAll();
		List<TaskBoardUserResponseDto> taskBoardDtoList = taskBoardList.stream()
				.map(taskBoard -> getTaskBoardDto(taskBoard)).collect(Collectors.toList());
		return taskBoardDtoList;
	}

	@Override
	public List<TaskBoardUserResponseDto> getTaskBoardDetailsByAssignorUserIdAndSpecificDate(String userSapId,
			String date) {
		Optional<List<TaskBoard>> taskBoardList = Optional.empty();
		Optional<User> userOptional = userRepository.findByUserSapIdAndRoles(userSapId,
				ApplicationConstants.ROLE_MANAGER);
		Optional<User> userOptional1 = userRepository.findByUserSapIdAndRoles(userSapId,
				ApplicationConstants.ROLE_USER);
		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("UserSapId is incorrect or user is previliged to perform this action",
					HttpStatus.BAD_REQUEST);
		if(userOptional.isPresent())
		{
		 taskBoardList = taskBoardRepository
				.findByAssignorUserSapIdAndTaskLastUpdatedDate(userSapId, LocalDate.parse(date));
		}
		else
		{
			
			if (!userOptional1.isPresent())
				throw new ResourceNotFoundException("UserSapId is incorrect or user is previliged to perform this action",
						HttpStatus.BAD_REQUEST);
			 taskBoardList = taskBoardRepository
						.findByAssigneeUserSapIdAndTaskLastUpdatedDate(userSapId, LocalDate.parse(date));
		}
		
		if(taskBoardList.isEmpty())
			throw new ResourceNotFoundException("No tasks found", HttpStatus.BAD_REQUEST);
		List<TaskBoardUserResponseDto> taskBoardDtoList = taskBoardList.get().stream()
				.map(taskBoard -> getTaskBoardDto(taskBoard)).collect(Collectors.toList());
		return taskBoardDtoList;
	}

	@Override
	public void updateTaskBoardByAssigneeAndUserSapId(String taskId, String userSapId) {
		Optional<User> userOptional = userRepository.findByUserSapId(userSapId);
		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("User is not valid", HttpStatus.BAD_REQUEST);
		Optional<TaskBoard> taskBoardOptional = taskBoardRepository
				.findByAssigneeUserSapIdAndTaskIdAndIsActive(userSapId, taskId, Boolean.TRUE);
		if (!taskBoardOptional.isPresent())
			throw new ResourceNotFoundException("TaskId and Assigned User didnot match", HttpStatus.BAD_REQUEST);
		Optional<Task> taskOptional = taskRepository.findByTaskIdAndIsActive(taskId, Boolean.TRUE);
		if (!taskOptional.isPresent())
			throw new ResourceNotFoundException("Selected task is invalid or Inactive", HttpStatus.NOT_FOUND);
		taskBoardOptional.get().setIsActive(!(taskBoardOptional.get().getIsActive()));
		taskBoardRepository.save(taskBoardOptional.get());
		taskOptional.get().setIsActive(!(taskOptional.get().getIsActive()));
		taskRepository.save(taskOptional.get());

	}

}
