package com.globalsoftwaresupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globalsoftwaresupport.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

	User findByUserId(Long userId);
	void deleteByUserId(Long userId);
}
