package com.example;

import com.example.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringWebfluxWebclientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxWebclientApplication.class, args);
    }

    @Autowired
    private WebClient webClient;

    @Override
    public void run(String... args) throws Exception {
        httpPost();
        httpPut();
        httpPatch();
        //httpDelete();
        httpGet();
        //httpTimedOut();
    }

    private void httpTimedOut() {
        webClient
                .get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(Post[].class)
                .timeout(Duration.ofSeconds(1))  // max wait time 1 sec
                .flatMapIterable(Arrays::asList)
                .onErrorReturn(new Post(100, "default title", "default author"))
                .subscribe(System.out::println);
    }

    private void httpDelete() {
        webClient
                .delete()
                .uri("/posts/2")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);
    }

    private void httpPatch(){
        System.out.println(" ----- HTTP PUT ------");
        webClient
                .patch()
                .uri("/posts/2")
                .body(BodyInserters.fromFormData("title", "webclient-patch"))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);
    }

    private void httpPut() {
        System.out.println(" ----- HTTP PUT ------");
        Post post = Post.builder().id(2).author("Shashi Tharoor Sir").title("An Era of Darkness").build();
        webClient
                .put()
                .uri("/posts/2")
                .bodyValue(post)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);
    }

    private void httpPost() {
        System.out.println(" ----- HTTP POST ------");
        Post post = Post.builder().id(2).author("Shashi Tharoor").title("An Era of Darkness").build();

        webClient.post()
                .uri("/posts")
                .bodyValue(post)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);
    }

    private void httpGet() {
        System.out.println(" ----- HTTP GET ------");
        webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(Post[].class)
                .flatMapIterable(Arrays::asList)
                .subscribe(System.out::println);
    }
}