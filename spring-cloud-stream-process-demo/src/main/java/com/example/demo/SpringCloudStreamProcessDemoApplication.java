package com.example.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Log4j2
@SpringBootApplication
public class SpringCloudStreamProcessDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamProcessDemoApplication.class, args);
    }

	// producer-out-0
    @Bean
    public Supplier<Flux<Long>> producer() {
        return () -> Flux.interval(Duration.ofSeconds(1)).log();
    }

	// processor-in-0
	// processor-out-0
    @Bean
    public Function<Flux<Long>, Flux<Long>> processor() {
        return longFlux -> longFlux.map(number -> number * number).log();
    }

	// consumer-in-0
    @Bean
    public Consumer<Long> consumer() {
		return number ->  log.info("Squared Number : {}", number);
    }
}