package com.example.demo.publisher;

import org.springframework.cloud.stream.annotation.EnableBinding;


// Enables the binding of targets annotated with Input and Output to a broker, according to the list of interfaces 
// passed as value to the annotation.
@EnableBinding(FoodOrderSource.class)
public class FoodOrderPublisher {
}