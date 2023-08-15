package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	
}