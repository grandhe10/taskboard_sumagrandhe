package com.demo.taskboard.service;



import com.demo.taskboard.dto.UserRequestDto;
import com.demo.taskboard.dto.UserResponseDto;


public interface UserService {
	
	/**
	 * This method is used to add users
	 * @param userRequestDto
	 * @return userResponseDto 
	 */
  UserResponseDto  saveUserDetails(UserRequestDto userRequestDto);

	/**
	 * This method is used delete Specific user
	 * @param userSapId
	 */
  void deleteUser(String userSapId);

	/**
	 * This method is used to modify the state of user
	 * @param userSapId
	 */
  void modifyUserState(String userSapId);
}
