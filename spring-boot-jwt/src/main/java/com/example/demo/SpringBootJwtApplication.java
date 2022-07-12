package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.AppUserRole;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class SpringBootJwtApplication implements CommandLineRunner{
	
	final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		//saveData();
	}
	
	private void saveData() {
		AppUser admin = new AppUser();
	    admin.setUsername("admin");
	    admin.setPassword("admin");
	    admin.setEmail("admin@email.com");
	    admin.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_ADMIN)));

	    userService.signup(admin);

	    AppUser client = new AppUser();
	    client.setUsername("client");
	    client.setPassword("client");
	    client.setEmail("client@email.com");
	    client.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_CLIENT)));

	    userService.signup(client);
	}
	
}
