package com.javatechie.querydsl.controller;

import com.javatechie.querydsl.dto.AuthorStatistic;
import com.javatechie.querydsl.entity.Author;
import com.javatechie.querydsl.entity.Book;
import com.javatechie.querydsl.service.AuthBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class AuthBookController {

    @Autowired
    private AuthBookService authBookService;

    @PostMapping(value = "/authors/book", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public List<Author> saveAuthorsWithBooks(@RequestBody List<Author> authors) {
        return authBookService.saveAuthorsWithBooks(authors);
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authBookService.getAuthors();
    }

    @GetMapping("/books")
    public List<Book> getALlBooks() {
        return authBookService.getBooks();
    }

    @GetMapping("/author/{email}")
    public Optional<Author> findAuthorByEmail(@PathVariable String email) {
        return authBookService.findAuthorByEmail(email);
    }

    @GetMapping("/authorStatistic")
    public List<AuthorStatistic> getAuthorStatistic() {
        return authBookService.getAuthorStatistic();
    }

    //to avoid N+1 problem in hibernate/jpa
    @GetMapping("/authors/fetchJoin")
    public List<Author> getAuthorsWithFetchJoin() {
        return authBookService.getAuthorsWithFetchJoin();
    }
}
