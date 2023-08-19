package com.example.demo.service;

import com.example.demo.dto.UserModel;
import com.example.demo.entity.User;

public interface UserService {
	
	User createUser(UserModel user);
	
	User readUser();
	
	User updateUser(UserModel user);
	
	void deleteUser();
	
	User getLoggedInUser();
}