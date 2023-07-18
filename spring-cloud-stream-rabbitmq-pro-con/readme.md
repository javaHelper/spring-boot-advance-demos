# Spring Cloud Stream RabbitMQ

# Producer 
`application.properties`

```properties
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

spring.cloud.stream.bindings.foodOrdersChannel.destination=foodOrders
```



FoodOrderSource.java

```java
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface FoodOrderSource {

	// Defines methods for sending messages.
	@Output("foodOrdersChannel")
	MessageChannel foodOrders();
}
```

```java
// Enables the binding of targets annotated with Input and Output to a broker, according to the list of interfaces 
// passed as value to the annotation.
@EnableBinding(FoodOrderSource.class)
public class FoodOrderPublisher {
}
```



# Consumer 

```properties
server.port=8081

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest


spring.cloud.stream.bindings.input.destination=foodOrders
spring.cloud.stream.bindings.input.group=foodOrdersIntakeGroup
spring.cloud.stream.bindings.input.content-type=application/json
```

FoodOrderConsumer.java

```java
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class FoodOrderConsumer {
	
	@StreamListener(target = Sink.INPUT)
	public void processCheapMeals(String meal) throws Exception {
		if (meal.contains("vegetables"))
			throw new Exception("Vegetables! Move to dead letter queue!");
		if (meal.contains("poison"))
			throw new Exception("Poison! Move to dead letter queue!");
		
		System.out.println("Meal consumed: " + meal);
	}
}
```


--------

--------

<img width="1018" alt="Screenshot 2023-07-18 at 8 11 14 PM" src="https://github.com/javaHelper/spring-boot-advance-demos/assets/54174687/fa34568c-269e-48be-93c6-eaf936650c36">

<img width="1011" alt="Screenshot 2023-07-18 at 8 11 28 PM" src="https://github.com/javaHelper/spring-boot-advance-demos/assets/54174687/3b0bc347-d372-452a-92ff-8e8e982dd2a6">

<img width="724" alt="Screenshot 2023-07-18 at 8 12 32 PM" src="https://github.com/javaHelper/spring-boot-advance-demos/assets/54174687/2f977f1c-8e64-4122-b6d4-8a7db2639c03">

<img width="1217" alt="Screenshot 2023-07-18 at 8 12 42 PM" src="https://github.com/javaHelper/spring-boot-advance-demos/assets/54174687/75255efd-8030-486f-b3b8-ccb5d7abc7d6">
