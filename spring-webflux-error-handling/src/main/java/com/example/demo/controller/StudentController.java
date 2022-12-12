package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Student;
import com.example.demo.service.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("all")
	public Flux<Student> getAll() throws Exception {
		return this.studentService.getAllStudents();
	}

	@GetMapping("{id}")
	public Mono<Student> getById(@PathVariable("id") int id) {
		return this.studentService.findStudentById(id);
	}

}