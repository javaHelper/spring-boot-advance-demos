# Spring Cloud Stream Demo

https://prateek-ashtikar512.medium.com/spring-cloud-stream-demo1-55407560d227

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class SpringCloudStreamProcessDemoApplication {

   public static void main(String[] args) {
      SpringApplication.run(SpringCloudStreamProcessDemoApplication.class, args);
   }

   // uppercase-in-0
   // uppercase-out-0
   @Bean
   public Function<String, String> toUppercase(){
      //return s -> s.toUpperCase();
      return String::toUpperCase;
   }
}
```

<img width="1340" alt="Screenshot 2022-08-29 at 6 59 06 PM" src="https://user-images.githubusercontent.com/54174687/187214157-474fea96-0004-45dd-b4aa-9f5ffdde2fb2.png">

```sh
kafka-console-producer --bootstrap-server localhost:9092 --topic toUppercase-in-0
>hello spring
>hello
>java 9
      
kafka-console-consumer --bootstrap-server localhost:9092 --topic toUppercase-out-0
HELLO SPRING
HELLO
JAVA 9
```
