package com.demo.taskboard.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.demo.taskboard.constants.ApplicationConstants;
import com.demo.taskboard.dao.UserRepository;
import com.demo.taskboard.dto.UserRequestDto;
import com.demo.taskboard.dto.UserResponseDto;
import com.demo.taskboard.exception.ResourceNotFoundException;
import com.demo.taskboard.model.User;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	UserResponseDto userResponseDto;
	
	
	@BeforeEach
	public void setUp() {

	}
		
	@Test
	public void addUserTest() {
		UserRequestDto userRequestDto  = new UserRequestDto();
		userRequestDto.setIsActive(Boolean.TRUE);
		userRequestDto.setUin("301588181");
		userRequestDto.setUserCreatedBy("324DSFWD4");
		userRequestDto.setUserFirstName("Edward");
		userRequestDto.setUserLastName("Thomas");
		       
     UserResponseDto userResponseDto = new UserResponseDto();
     userResponseDto.setUserSapId("2385JDHSJ-9");
		User user = new User();
		user.setIsActive(Boolean.TRUE);
		user.setUin("301588181");
		user.setUserCreatedBy("324DSFWD4");
		user.setUserFirstName("Edward");
		user.setUserLastName("Thomas");
	
		when(userRepository.findByUin(any(String.class))).thenReturn(Optional.of(user));
		when(userRepository.findByUserSapId(any(String.class))).thenReturn(Optional.of(user));
		userServiceImpl.saveUserDetails(userRequestDto);
		verify(userRepository).save(user);

	}
	
	@Test
	public void addUserTest1() throws ResourceNotFoundException{
		
		UserRequestDto userRequestDto  = new UserRequestDto();
		userRequestDto.setIsActive(Boolean.TRUE);
		userRequestDto.setUin("301588181");
		userRequestDto.setUserCreatedBy("324DSFWD4");
		userRequestDto.setUserFirstName("Edward");
		userRequestDto.setUserLastName("Thomas");
		       
     UserResponseDto userResponseDto = new UserResponseDto();
     userResponseDto.setUserSapId("2385JDHSJ-9");
		User user = new User();
		user.setIsActive(Boolean.TRUE);
		user.setUin("301588181");
		user.setUserCreatedBy("324DSFWD4");
		user.setUserFirstName("Edward");
		user.setUserLastName("Thomas");
		
	    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
	        userServiceImpl.saveUserDetails(userRequestDto);
	    });
	 
	    String expectedMessage = ApplicationConstants.USER_NOT_FOUND;
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));

	}
	
}