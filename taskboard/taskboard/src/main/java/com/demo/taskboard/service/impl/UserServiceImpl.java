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
import com.demo.taskboard.dao.UserRepository;
import com.demo.taskboard.dto.UserRequestDto;
import com.demo.taskboard.dto.UserResponseDto;
import com.demo.taskboard.exception.ResourceExistException;
import com.demo.taskboard.exception.ResourceNotFoundException;
import com.demo.taskboard.model.User;
import com.demo.taskboard.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	private static final String ROLE_USER = "ROLE_USER";
	Log logger = LogFactory.getLog(UserServiceImpl.class);
	@Autowired
	UserRepository userRepository;
	@Override
	public UserResponseDto saveUserDetails(UserRequestDto userRequestDto) {
		logger.info("User Save Details method invocated");
		Optional<User> userOptional = userRepository.findByUin(userRequestDto.getUin());
		if(userOptional.isPresent())
			throw new ResourceExistException("User already exists",HttpStatus.EXPECTATION_FAILED);
		Optional<User> userOptional1 = userRepository.findByUserSapIdAndRoles(userRequestDto.getUserCreatedBy(),ApplicationConstants.ROLE_MANAGER);
		if(!userOptional1.isPresent())
			throw new ResourceNotFoundException("User is not previliged to do this action", HttpStatus.EXPECTATION_FAILED);
			User user = new User();
			if(userRequestDto.getIsActive()==null)
			user.setIsActive(Boolean.TRUE);
			user.setPassword(UUID.randomUUID().toString().substring(0, 8));
			user.setUserSapId(UUID.randomUUID().toString().replaceAll("_", "").substring(0, 10).toUpperCase());
			user.setRoles(ROLE_USER);
			user.setUserCreatedBy(userRequestDto.getUserCreatedBy());
			user.setUserCreatedDate(LocalDate.now());
			BeanUtils.copyProperties(userRequestDto,user);
			UserResponseDto userResponseDto = new UserResponseDto();
					userResponseDto.setUserSapId(userRepository.save(user).getUserSapId());
			return userResponseDto;
		
		}
	@Override
	public void deleteUser(String userSapId) {
		Optional<User> user = userRepository.findByUserSapId(userSapId);
		if(user.isPresent())
			userRepository.delete(user.get());
		else
			throw new ResourceNotFoundException("Please check the user details entered..",HttpStatus.BAD_REQUEST);
		
	}
	@Override
	public void modifyUserState(String userSapId) {
		Optional<User> userOptional = userRepository.findByUserSapId(userSapId);
		if(!userOptional.isPresent())
			throw new ResourceNotFoundException("Please check the user details entered..",HttpStatus.BAD_REQUEST);
		userOptional.get().setIsActive(!(userOptional.get().getIsActive()));
		userRepository.save(userOptional.get());		
	}
		
	}

