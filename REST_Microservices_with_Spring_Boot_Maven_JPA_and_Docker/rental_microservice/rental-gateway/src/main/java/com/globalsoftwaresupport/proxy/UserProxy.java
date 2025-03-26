package com.globalsoftwaresupport.proxy;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.globalsoftwaresupport.model.PatchUserRequest;
import com.globalsoftwaresupport.model.User;

@Component
public class UserProxy {

	private final RestTemplate restTemplate;
	private final String url;
	
	public UserProxy(@Value("${user.url}") String url, RestTemplate restTemplate) {
		this.url = url;
		this.restTemplate = restTemplate;
	}
	
	public User[] getUsers() {
		return restTemplate.getForObject(url + "v1", User[].class);
	}

	public void create(User user) {
		restTemplate.postForObject(url + "v1", user, User.class);
	}

	public void remove(String id) {
		restTemplate.delete(url + "v1/{id}", Map.of("id", id));
	}

	public void update(String id, HttpEntity<PatchUserRequest> request) {
		restTemplate.patchForObject(url + "v1/{id}", request, 
				Void.class, Map.of("id", id));
	}
}
