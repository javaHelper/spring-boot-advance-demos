package com.example.demo.service;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Student;
import com.example.demo.exception.StudentNotFoundException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

	private static final Map<Integer, Student> DB = Map.of(
			1, new Student(1, "John", "Doe"),
			2, new Student(2, "Mike", "Steggle"),
			3, new Student(3, "Matt", "Wixson"),
			4, new Student(4, "Neha", "Parate"),
			5, new Student(5, "Victoria", "Kerr"));

	public Flux<Student> getAllStudents() throws Exception {
		return Flux.fromIterable(DB.values())
				.doFirst(this::throwRandomError);
	}

	public Mono<Student> findStudentById(int id) {
		return Mono.just(id)
				.filter(DB::containsKey)
				.map(DB::get)
				.switchIfEmpty(Mono.error(() -> new StudentNotFoundException(id)));
	}

	private void throwRandomError(){
		var random = ThreadLocalRandom.current().nextInt(0, 10);
		if(random > 5) {
			throw new RuntimeException("some random error");
		}
	}

}