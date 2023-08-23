package com.demo.taskboard.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedHashMap;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.taskboard.dto.UserRequestDto;
import com.demo.taskboard.dto.UserResponseDto;
import com.demo.taskboard.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
    UserService userService;

	MockMvc mockMvc;
    ObjectMapper objectMapper;

    @InjectMocks
    UserController userController;
    
    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();   
        
    }
    
    @Test
    public void addUserTest() throws Exception
    {
		
    	UserRequestDto userRequestDto  = new UserRequestDto();
		userRequestDto.setIsActive(Boolean.TRUE);
		userRequestDto.setUin("301588181");
		userRequestDto.setUserCreatedBy("324DSFWD4");
		userRequestDto.setUserFirstName("Edward");
		userRequestDto.setUserLastName("Thomas");
		
       //String response = new String("New User added successfully and added userSapId: 2385JDHSJ-9");
       
     UserResponseDto userResponseDto = new UserResponseDto();
     userResponseDto.setUserSapId("2385JDHSJ-9");
     
        when(userService.saveUserDetails(any(UserRequestDto.class))).thenReturn(userResponseDto);
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.any(LinkedHashMap.class)));

        verify(userService).saveUserDetails(any(UserRequestDto.class));
    }

   
    
}