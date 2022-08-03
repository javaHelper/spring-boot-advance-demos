package com.javatechie.querydsl.repository.impl;

import com.javatechie.querydsl.entity.Book;
import com.javatechie.querydsl.repository.BookRepository;

import javax.persistence.EntityManager;

public class BookRepositoryImpl extends BaseRepositoryImpl<Book,Integer> implements BookRepository {

    public BookRepositoryImpl( EntityManager em) {
        super(Book.class, em);
    }
}
