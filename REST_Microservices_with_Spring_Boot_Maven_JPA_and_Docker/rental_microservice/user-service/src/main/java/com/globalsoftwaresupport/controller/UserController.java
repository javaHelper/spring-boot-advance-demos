package com.globalsoftwaresupport.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.globalsoftwaresupport.model.PatchUserRequest;
import com.globalsoftwaresupport.model.User;
import com.globalsoftwaresupport.service.UserService;

@RestController
@RequestMapping("/v1")
public class UserController {

	@Autowired
	private UserService service;

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") String userId, @RequestBody PatchUserRequest request) {
		var user = Optional.ofNullable(userId).map(u -> Long.valueOf(userId)).map(service::getUser).orElseThrow();

		service.update(user, request);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String userId) {
		var user = Optional.ofNullable(userId).map(u -> Long.valueOf(userId)).map(service::getUser).orElseThrow();

		service.delete(user.getUserId());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody User user) {
		service.create(user);
	}

	@GetMapping
	public List<User> getUsers() {
		return service.getUsers();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable("id") String userId) {
		var user = Optional.ofNullable(userId).map(u -> Long.valueOf(userId)).map(service::getUser).orElseThrow();

		return user;
	}
}
