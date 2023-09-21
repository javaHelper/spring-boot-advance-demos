package com.example;

import com.example.entity.Gender;
import com.example.entity.Student;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootThymeleafStudentMgmtApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootThymeleafStudentMgmtApplication.class, args);
    }

    @Autowired
    private StudentRepository repository;

    @Override
    public void run(String... args) throws Exception {
        //repository.save(Student.create(null, "Mike", "Doe", "mike.doe@gmail.com", Gender.MALE));
    }
}