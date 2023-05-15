package com.petrotec.service;

import java.util.List;

import com.petrotec.entity.User;
import com.petrotec.payload.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto, int userId);
	
	UserDto getUserByID (Integer userId);
	
	List<UserDto> getAllUser();
	
	void deleteUser(int userId);

}
