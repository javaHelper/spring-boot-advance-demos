package com.globalsoftwaresupport.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import com.globalsoftwaresupport.model.PatchUserRequest;
import com.globalsoftwaresupport.model.User;
import com.globalsoftwaresupport.proxy.UserProxy;

@RestController
@RequestMapping("v1/users")
public class GatewayUserController {

	@Autowired
	private UserProxy userProxy;
	
	@PatchMapping("/{id}")
	public void update(@PathVariable(name = "id") String id, 
			@RequestBody PatchUserRequest request) {
		userProxy.update(id, new HttpEntity<PatchUserRequest>(request));
	}
	
	@GetMapping
	public User[] getUsers() {
		return userProxy.getUsers();
	}
	
	@PostMapping
	public void create(@RequestBody User user) {
		userProxy.create(user);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") String id) {
		userProxy.remove(id);
	}
}
