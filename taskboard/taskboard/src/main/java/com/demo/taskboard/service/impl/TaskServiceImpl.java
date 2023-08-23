package com.demo.taskboard.service.impl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demo.taskboard.constants.ApplicationConstants;
import com.demo.taskboard.dao.TaskRepository;
import com.demo.taskboard.dao.UserRepository;
import com.demo.taskboard.dto.TaskRequestDto;
import com.demo.taskboard.dto.TaskResponseDto;
import com.demo.taskboard.exception.ResourceNotFoundException;
import com.demo.taskboard.model.Task;
import com.demo.taskboard.model.User;
import com.demo.taskboard.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

	Log logger = LogFactory.getLog(TaskServiceImpl.class);
	@Autowired
	UserRepository userRepository;
	@Autowired
	TaskRepository taskRepository;


	@Override
	public TaskResponseDto saveTaskDetails(TaskRequestDto taskDto) {
		
		Optional<User> userOptional = userRepository.findByUserSapIdAndRoles(taskDto.getTaskCreatedBy(),ApplicationConstants.ROLE_MANAGER);
		if(userOptional.isPresent())
		{
			logger.info("User Details are verified successfully!!!");
			//List<TaskResponseDto> taskResponseDtoList = taskRequestDto.stream().map(taskRequestDto->saveTaskFromTaskRequestDtoDetails(taskRequestDto,userOptional.get())).collect(Collectors.toList());
			//TaskResponseDtoToUser taskResponseDtoToUser = new TaskResponseDtoToUser();
			TaskResponseDto taskResponseDto = saveTaskFromTaskRequestDtoDetails(taskDto,userOptional.get());
			logger.info("Task Details are added successfully!!!");
			return taskResponseDto;
		}
		else
			throw new ResourceNotFoundException("User Details are incorrect or User does not have enough previleges", HttpStatus.BAD_REQUEST);
		
	}

	private TaskResponseDto saveTaskFromTaskRequestDtoDetails(TaskRequestDto taskRequestDto,User user) {
		Task task = new Task();
		if(taskRequestDto.getIsActive()==null)
			task.setIsActive(Boolean.TRUE);
		task.setLastUpdatedBy(user.getUserSapId());
		task.setLastUpdatedDate(LocalDate.now());
		task.setUser(user);
		task.setTaskCreatedDate(LocalDate.now());
		task.setTaskId(UUID.randomUUID().toString().replaceAll("_", "").replaceAll("-","").substring(0, 10).toUpperCase());
		BeanUtils.copyProperties(taskRequestDto,task);
		TaskResponseDto taskResponseDto = new TaskResponseDto();
		taskRepository.save(task);
		logger.info("task created ...");
		BeanUtils.copyProperties(task, taskResponseDto);
		taskResponseDto.setTaskCreatedBy(user.getUserSapId());
		return taskResponseDto ;
	}
	
	

}
