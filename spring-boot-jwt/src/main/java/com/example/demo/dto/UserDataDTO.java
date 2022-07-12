package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.AppUserRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDataDTO {

	private String username;
	private String email;
	private String password;
	List<AppUserRole> appUserRoles;
}