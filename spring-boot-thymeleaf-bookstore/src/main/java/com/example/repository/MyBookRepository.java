package com.example.repository;

import com.example.entity.MyBookList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBookRepository extends JpaRepository<MyBookList, Integer> {

}