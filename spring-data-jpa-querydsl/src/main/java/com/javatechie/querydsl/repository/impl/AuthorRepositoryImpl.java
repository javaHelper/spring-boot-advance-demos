package com.javatechie.querydsl.repository.impl;

import com.javatechie.querydsl.dto.AuthorStatistic;
import com.javatechie.querydsl.entity.Author;
import com.javatechie.querydsl.repository.AuthorRepository;
import com.javatechie.querydsl.service.AuthBookService;
import com.querydsl.core.types.Projections;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class AuthorRepositoryImpl extends BaseRepositoryImpl<Author, Integer> implements AuthorRepository {

    public AuthorRepositoryImpl(EntityManager em) {
        super(Author.class, em);
    }

    @Override
    public Optional<Author> findAuthorByEmail(String email) {
        return Optional.ofNullable(jpaQueryFactory
                .select(author)
                .from(author)
                .where(author.email.equalsIgnoreCase(email))
                .fetchFirst());
    }

    @Override
    public List<AuthorStatistic> getAuthorStatistic() {
        return jpaQueryFactory
                .from(author)
                .innerJoin(author.books, book)
                .groupBy(author.name)
                .select(Projections.constructor(AuthorStatistic.class, author.name, book.count()))
                .fetch();
    }

    @Override
    public List<Author> getAuthors() {
        return jpaQueryFactory
                .select(author)
                .distinct()
                .from(author)
                .innerJoin(author.books, book)
                .fetchJoin().fetch();
    }
}
