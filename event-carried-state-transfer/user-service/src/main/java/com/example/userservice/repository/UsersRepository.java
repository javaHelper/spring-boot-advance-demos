package com.example.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
}