package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;

public enum AppUserRole implements GrantedAuthority {
	ROLE_ADMIN, ROLE_CLIENT;

	public String getAuthority() {
		return name();
	}

}