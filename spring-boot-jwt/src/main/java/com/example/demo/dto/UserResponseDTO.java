package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.AppUserRole;

import lombok.Data;

@Data
public class UserResponseDTO {
	private Integer id;
	private String username;
	private String email;
	List<AppUserRole> appUserRoles;
}