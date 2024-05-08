package com.bookstore.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import org.springframework.beans.factory.ObjectFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;


public class BookListener {
	
	@Autowired
	private ObjectFactory<AuthorRepository> authorRepository;
	

	@PrePersist
    void onPrePersist(Book book) {
        System.out.println("BookListener.onPrePersist(): " + book);
    }

    @PostPersist
    void onPostPersist(Book book) {
        System.out.println("BookListener.onPostPersist(): " + book);
    }

    @PostLoad
    void onPostLoad(Book book) {
        System.out.println("BookListener.onPostLoad(): " + book);
    }

    @PreUpdate
    void onPreUpdate(Book book) {
        System.out.println("BookListener.onPreUpdate(): " + book);
    }

    @PostUpdate
    void onPostUpdate(Book book) {
        System.out.println("BookListener.onPostUpdate(): " + book);
       // authorRepository.findAll()
    }

    @PreRemove
    void onPreRemove(Book book) {
        System.out.println("BookListener.onPreRemove(): " + book);
        System.out.println(authorRepository.getObject().count());
    }

    @PostRemove
    void onPostRemove(Book book) {
        System.out.println("BookListener.onPostRemove(): " + book);
    }
}
