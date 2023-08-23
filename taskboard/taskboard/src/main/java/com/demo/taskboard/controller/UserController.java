package com.demo.taskboard.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.demo.taskboard.dto.UserRequestDto;
import com.demo.taskboard.dto.UserResponseDto;
import com.demo.taskboard.service.UserService;

@RestController

public class UserController {

	Log logger = LogFactory.getLog(UserController.class);
	@Autowired
	UserService userService;

	@PostMapping("/users")
	public ResponseEntity<UserResponseDto> addUser( @Valid @RequestBody UserRequestDto userRequestDto) {
		logger.info("User add requested..");
		UserResponseDto userResponseDto = userService.saveUserDetails(userRequestDto);
		logger.info("User added Successfully..");
		return new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.CREATED);

	}
	
	@DeleteMapping("/users/{userSapId}")
	public ResponseEntity<String> deleteUser( @PathVariable String userSapId) {
		logger.info("User delete requested..");
		 userService.deleteUser(userSapId);
		logger.info("User deleted Successfully..");
		return new ResponseEntity<String>("User Deleted successfully", HttpStatus.OK);

	}
}
