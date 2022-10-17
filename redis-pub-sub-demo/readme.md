# Redis PubSub With Spring Boot


# Overview:
In this article, I would like to show Redis PubSub with Spring Boot which can be used to broadcast messages across multiple services in a Microservices architecture. I assume you have basic knowledge on Redis + Spring Boot integration. If you are new to Spring Boot Redis, check the below article.

# Redis PubSub:
PubSub is an asynchronous messaging model for service-to-service communication in Microservices architecture. That is, a service (Publisher) instead of sending a message to a specific recipient, it publishes the message to a Topic/Channel, through which interested parties (Subscribers) receive the message.

<img width="586" alt="Screenshot 2022-10-17 at 6 01 45 PM" src="https://user-images.githubusercontent.com/54174687/196177726-688d5b0e-5ca7-43d5-b5bf-7e74dee218fb.png">

| Advantages                  |   	                                                                                                            | 
|---	                        |---	                                                                                                            |
| One-To-Many Communication  	| A Publisher can publish a single message where N number of subscribers can receive and react to the message.  	| 
|Loose Coupling               |Services are not tightly coupled. Any service can consume / ignore the message                                   |
|Better Performance           | Publisher does not have to call N number of services. Instead it just publishes a message into a topic. It does not have to have any knowledge on the subscribers. It is not blocked. |

The PubSub model also has some limitations.

|Limitations|
|----|
|PubSub is fire-forget model. If the receivers are offline, they might not receive the message. (Take a look at Redis Stream for this use case)|
|PubSub is fan-out model. That is, multiple instances of the same service will receive the message. (Take a look at Redis Stream for this use case)|

# Sample Application:
We are going to create a 2 simple Spring Boot applications. 1 will be acting like a publisher and other one will be a subscriber.

- Publisher: this application will periodically publish Jokes
- Subscriber: There could be N number of subscribers. In our case we will have 1 subscriber.
  - This subscriber will be notified as and when a new joke is published.
  - Subscriber could do anything with the joke. In our case we will just print on the console.


# Jokes API:
We will be using below URL to get random jokes. That is our publisher will use the below URL to get random jokes and publish it. It is a simple GET request without any authentication.

https://joke.deno.dev/
The response payload is as shown below. Jokes are in the Q & A format.

```
{
   "id":120,
   "type":"general",
   "setup":"How do hens stay fit?",
   "punchline":"They always egg-cercise!"
}
```

# Dockerizing Infrastructure:

I use below Dockerfile to dockerize both publisher and subscriber applications.

```sh
# Use JRE11 slim
FROM openjdk:11.0-jre-slim

# Add the app jar
ADD target/*.jar redis-pubsub.jar

ENTRYPOINT java -jar redis-pubsub.jarCopy
```

docker-compose file with all the dependencies

```sh
version: '3'
services:
  redis:
    image: redis
    ports:
      - 6379:6379
  publisher:
    build: ./redis-publisher
    image: vinsdocker/redis-publisher
    depends_on:
      - redis
    environment:
      - SPRING_REDIS_HOST=redis
  subscriber:
    build: ./redis-subscriber
    image: vinsdocker/redis-subscriber
    depends_on:
      - redis
    environment:
      - SPRING_REDIS_HOST=redis
```

# Redis PubSub Spring Boot – Demo:
Once everything is ready, I run these commands one by one.

# Build the project
```mvn clean package -DskipTests```

# Build the docker images
 ```docker-compose build```
 
# Run the applications
```docker-compose up```
