package com.javatechie.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T,ID> {
    T findByIdMandatory(ID id) throws IllegalArgumentException;
}
