package com.example.repository;

import com.example.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department findByName(String name);
}
