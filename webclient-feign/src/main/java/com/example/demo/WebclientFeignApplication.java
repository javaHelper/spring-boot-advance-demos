package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@EnableReactiveFeignClients
@SpringBootApplication
public class WebclientFeignApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WebclientFeignApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Flux.concat(
                getAll(), // first get all movies
                post(),   // create a new movie id 5
                get(),    // get movie id 5 - check if it is present
                put(),    // update movie id 5
                get(),    // get movie id 5 - check if it is updated
                delete()  // delete movie id 5
        ).subscribe();
    }

    @Autowired
    private MovieClient movieClient;

    // GET all movies
    private Mono<Void> getAll(){
        return this.movieClient.getAllMovies()
                .doOnNext(System.out::println)
                .doFinally(s -> System.out.println("------------- GET All completed ------------------"))
                .then();
    }

    // POST a movie
    private Mono<Void> post(){
        MovieDto dto = MovieDto.create(
                5,
                "Harry Potter and the Sorcerer Stone",
                1999,
                7.6
        );
        return this.movieClient.saveMovie(dto)
                .doFinally(s -> System.out.println("------------- POST Movie completed ------------------"))
                .then();
    }

    // GET a specific movie
    private Mono<Void> get(){
        return this.movieClient.getMovie(5)
                .doOnNext(System.out::println)
                .doFinally(s -> System.out.println("------------- GET Movie completed ------------------"))
                .then();
    }

    // PUT update a movie
    private Mono<Void> put(){
        MovieDto dto = MovieDto.create(
                null,
                "Harry Potter and the Sorcerer Stone",
                2001,
                7.6
        );
        return this.movieClient.updateMovie(5, dto)
                .doOnNext(System.out::println)
                .doFinally(s -> System.out.println("------------- Movie updated ------------------"))
                .then();
    }

    // DELETE a movie
    private Mono<Void> delete(){
        return this.movieClient.deleteMovie(5)
                .doOnNext(System.out::println)
                .doFinally(s -> System.out.println("------------- Movie deleted ------------------"))
                .then();
    }
}