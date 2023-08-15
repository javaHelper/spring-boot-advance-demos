package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class AuthorityModel implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authority;

    public AuthorityModel(String authority) {
        this.authority = authority;
    }

	@Override
	public String getAuthority() {
		return authority;
	}
}