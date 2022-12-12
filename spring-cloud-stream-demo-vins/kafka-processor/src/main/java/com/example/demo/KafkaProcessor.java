package com.example.demo;

import java.util.function.Function;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProcessor {
	@Bean
    public Function<KStream<String, Long>, KStream<String, Long>> evenNumberSquareProcessor(){
        return kStream ->  kStream
                            .filter((k, v) -> v % 2 == 0)
                            .peek((k, v) -> System.out.println("Squaring Even : " + v))
                            .mapValues(v -> v * v);
    };
}
