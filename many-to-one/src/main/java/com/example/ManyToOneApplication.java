package com.example;

import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManyToOneApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ManyToOneApplication.class, args);
	}

	@Autowired
	private EmployeeService employeeService;

	@Override
	public void run(String... args) throws Exception {
		employeeService.insertDepartmentWithEmployees();

		employeeService.deleteEmployeeOfDept();
	}
}
