package com.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.service.BookstoreService;

@SpringBootApplication
public class MainApplication {

    @Autowired
    private BookstoreService bookstoreService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\n\npersistAuthorWithBooks():");
            bookstoreService.persistAuthorWithBooks();
            
            bookstoreService.update();

            System.out.println("\n\nfetchAndRemovePaperback():");
            bookstoreService.fetchAndRemovePaperback();

            System.out.println("\n\nfetchAndRemoveEbook():");
            bookstoreService.fetchAndRemoveEbook();
        };
    }
}
