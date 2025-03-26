package com.globalsoftwaresupport.service;

import java.util.List;

import com.globalsoftwaresupport.model.PatchUserRequest;
import com.globalsoftwaresupport.model.User;

public interface UserService {
	public List<User> getUsers();
	public User getUser(Long userId);
	public void create(User user);
	public void delete(Long id);
	public void update(User user, PatchUserRequest request);
}
