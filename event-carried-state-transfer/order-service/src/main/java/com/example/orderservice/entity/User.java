package com.example.orderservice.entity;

import lombok.Data;

@Data
public class User {
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
}
