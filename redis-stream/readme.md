# Redis Stream

<img width="867" alt="Screenshot 2022-12-28 at 7 18 57 PM" src="https://user-images.githubusercontent.com/54174687/209822301-0d55fe0b-11be-4813-a676-fc83c4a3a4ce.png">



- Our publisher will publish some evens to related to purchases into Redis. Lets call them purchase-events stream.

- A consumer group is interested in listening to those events. This could be for calculating the revenue or processing payment or sending out an email!
   - When you need to do all of these – for ex: payment processing and sending out an email then you need a separate consumer group for each.
   
- The consumers will be consuming events & they can do anything with it. In our case we just find the price user paid and calculate the revenue by category.
    - This can be logged in a separate DB. But to keep things simple, I will be logging this info as a SortedSet in Redis.


Lets start the redis-commander first to execute below commands 

```sh
docker-compose up redis redis-commander
```

```sh
127.0.0.1:6379> XADD purchase-events * dummy-key dummy-value
"1672231823127-0"

127.0.0.1:6379> XGROUP CREATE purchase-events purchase-events $
OK
```

-------

# Producer 

```
/Library/Java/JavaVirtualMachines/jdk-11.0.13.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=58240:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/prats/Documents/workspace/redis-stream/redis-stream-producer/target/classes:/Users/prats/Documents/workspace/redis-stream/common-dto/target/classes:/Users/prats/.m2/repository/org/springframework/boot/spring-boot-starter-data-redis-reactive/2.7.1/spring-boot-starter-data-redis-reactive-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/boot/spring-boot-starter-data-redis/2.7.1/spring-boot-starter-data-redis-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/data/spring-data-redis/2.7.1/spring-data-redis-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/data/spring-data-keyvalue/2.7.1/spring-data-keyvalue-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/data/spring-data-commons/2.7.1/spring-data-commons-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/spring-tx/5.3.21/spring-tx-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/spring-beans/5.3.21/spring-beans-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/spring-oxm/5.3.21/spring-oxm-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/spring-aop/5.3.21/spring-aop-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/spring-context-support/5.3.21/spring-context-support-5.3.21.jar:/Users/prats/.m2/repository/io/lettuce/lettuce-core/6.1.8.RELEASE/lettuce-core-6.1.8.RELEASE.jar:/Users/prats/.m2/repository/io/netty/netty-common/4.1.78.Final/netty-common-4.1.78.Final.jar:/Users/prats/.m2/repository/io/netty/netty-handler/4.1.78.Final/netty-handler-4.1.78.Final.jar:/Users/prats/.m2/repository/io/netty/netty-resolver/4.1.78.Final/netty-resolver-4.1.78.Final.jar:/Users/prats/.m2/repository/io/netty/netty-buffer/4.1.78.Final/netty-buffer-4.1.78.Final.jar:/Users/prats/.m2/repository/io/netty/netty-transport-native-unix-common/4.1.78.Final/netty-transport-native-unix-common-4.1.78.Final.jar:/Users/prats/.m2/repository/io/netty/netty-codec/4.1.78.Final/netty-codec-4.1.78.Final.jar:/Users/prats/.m2/repository/io/netty/netty-transport/4.1.78.Final/netty-transport-4.1.78.Final.jar:/Users/prats/.m2/repository/org/projectlombok/lombok/1.18.24/lombok-1.18.24.jar:/Users/prats/.m2/repository/org/springframework/boot/spring-boot-starter/2.7.1/spring-boot-starter-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/boot/spring-boot/2.7.1/spring-boot-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/spring-context/5.3.21/spring-context-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/spring-expression/5.3.21/spring-expression-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/2.7.1/spring-boot-autoconfigure-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/boot/spring-boot-starter-logging/2.7.1/spring-boot-starter-logging-2.7.1.jar:/Users/prats/.m2/repository/ch/qos/logback/logback-classic/1.2.11/logback-classic-1.2.11.jar:/Users/prats/.m2/repository/ch/qos/logback/logback-core/1.2.11/logback-core-1.2.11.jar:/Users/prats/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.17.2/log4j-to-slf4j-2.17.2.jar:/Users/prats/.m2/repository/org/apache/logging/log4j/log4j-api/2.17.2/log4j-api-2.17.2.jar:/Users/prats/.m2/repository/org/slf4j/jul-to-slf4j/1.7.36/jul-to-slf4j-1.7.36.jar:/Users/prats/.m2/repository/jakarta/annotation/jakarta.annotation-api/1.3.5/jakarta.annotation-api-1.3.5.jar:/Users/prats/.m2/repository/org/yaml/snakeyaml/1.30/snakeyaml-1.30.jar:/Users/prats/.m2/repository/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar:/Users/prats/.m2/repository/org/springframework/spring-core/5.3.21/spring-core-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/spring-jcl/5.3.21/spring-jcl-5.3.21.jar:/Users/prats/.m2/repository/io/projectreactor/reactor-core/3.4.19/reactor-core-3.4.19.jar:/Users/prats/.m2/repository/org/reactivestreams/reactive-streams/1.0.4/reactive-streams-1.0.4.jar com.vinsguru.producer.RedisStreamProducerApplication

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.1)

2022-12-28 19:11:31.734  INFO 81713 --- [           main] c.v.p.RedisStreamProducerApplication     : Starting RedisStreamProducerApplication using Java 11.0.13 on Prateeks-MacBook-Pro.local with PID 81713 (/Users/prats/Documents/workspace/redis-stream/redis-stream-producer/target/classes started by prateekashtikar in /Users/prats/Documents/workspace/redis-stream)
2022-12-28 19:11:31.737  INFO 81713 --- [           main] c.v.p.RedisStreamProducerApplication     : No active profile set, falling back to 1 default profile: "default"
2022-12-28 19:11:32.907  INFO 81713 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode
2022-12-28 19:11:32.910  INFO 81713 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data Redis repositories in DEFAULT mode.
2022-12-28 19:11:32.955  INFO 81713 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 25 ms. Found 0 Redis repository interfaces.
2022-12-28 19:11:34.654  INFO 81713 --- [           main] c.v.p.RedisStreamProducerApplication     : Started RedisStreamProducerApplication in 3.959 seconds (JVM running for 4.917)
1672234895768-0
Total Events :: 1
1672234895790-0
1672234896659-0
1672234897647-0
1672234898659-0
1672234899672-0
1672234900650-0
1672234901649-0
1672234902648-0
1672234903649-0
Total Events :: 11
1672234904650-0
1672234905652-0
1672234906647-0
1672234907646-0
1672234908656-0
1672234909651-0
1672234910653-0
1672234911653-0
1672234912656-0
1672234913665-0
Total Events :: 21
1672234914650-0
1672234915654-0
1672234916648-0
1672234917655-0
1672234918649-0
1672234919651-0
1672234920650-0
1672234921656-0
1672234922650-0
1672234923656-0
Total Events :: 31
1672234924650-0
1672234925655-0
1672234926654-0
1672234927650-0
1672234928653-0
1672234929658-0
1672234930649-0
1672234931648-0
1672234932651-0
1672234933647-0
Total Events :: 41
1672234934652-0
1672234935651-0

Process finished with exit code 130 (interrupted by signal 2: SIGINT)

```

-------

# Start the consumer 

```
/Library/Java/JavaVirtualMachines/jdk-11.0.13.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=58248:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/prats/Documents/workspace/redis-stream/redis-stream-consumer/target/classes:/Users/prats/Documents/workspace/redis-stream/common-dto/target/classes:/Users/prats/.m2/repository/org/springframework/boot/spring-boot-starter-data-redis-reactive/2.7.1/spring-boot-starter-data-redis-reactive-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/boot/spring-boot-starter-data-redis/2.7.1/spring-boot-starter-data-redis-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/data/spring-data-redis/2.7.1/spring-data-redis-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/data/spring-data-keyvalue/2.7.1/spring-data-keyvalue-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/data/spring-data-commons/2.7.1/spring-data-commons-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/spring-tx/5.3.21/spring-tx-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/spring-beans/5.3.21/spring-beans-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/spring-oxm/5.3.21/spring-oxm-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/spring-aop/5.3.21/spring-aop-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/spring-context-support/5.3.21/spring-context-support-5.3.21.jar:/Users/prats/.m2/repository/io/lettuce/lettuce-core/6.1.8.RELEASE/lettuce-core-6.1.8.RELEASE.jar:/Users/prats/.m2/repository/io/netty/netty-common/4.1.78.Final/netty-common-4.1.78.Final.jar:/Users/prats/.m2/repository/io/netty/netty-handler/4.1.78.Final/netty-handler-4.1.78.Final.jar:/Users/prats/.m2/repository/io/netty/netty-resolver/4.1.78.Final/netty-resolver-4.1.78.Final.jar:/Users/prats/.m2/repository/io/netty/netty-buffer/4.1.78.Final/netty-buffer-4.1.78.Final.jar:/Users/prats/.m2/repository/io/netty/netty-transport-native-unix-common/4.1.78.Final/netty-transport-native-unix-common-4.1.78.Final.jar:/Users/prats/.m2/repository/io/netty/netty-codec/4.1.78.Final/netty-codec-4.1.78.Final.jar:/Users/prats/.m2/repository/io/netty/netty-transport/4.1.78.Final/netty-transport-4.1.78.Final.jar:/Users/prats/.m2/repository/org/projectlombok/lombok/1.18.24/lombok-1.18.24.jar:/Users/prats/.m2/repository/org/springframework/boot/spring-boot-starter/2.7.1/spring-boot-starter-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/boot/spring-boot/2.7.1/spring-boot-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/spring-context/5.3.21/spring-context-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/spring-expression/5.3.21/spring-expression-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/2.7.1/spring-boot-autoconfigure-2.7.1.jar:/Users/prats/.m2/repository/org/springframework/boot/spring-boot-starter-logging/2.7.1/spring-boot-starter-logging-2.7.1.jar:/Users/prats/.m2/repository/ch/qos/logback/logback-classic/1.2.11/logback-classic-1.2.11.jar:/Users/prats/.m2/repository/ch/qos/logback/logback-core/1.2.11/logback-core-1.2.11.jar:/Users/prats/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.17.2/log4j-to-slf4j-2.17.2.jar:/Users/prats/.m2/repository/org/apache/logging/log4j/log4j-api/2.17.2/log4j-api-2.17.2.jar:/Users/prats/.m2/repository/org/slf4j/jul-to-slf4j/1.7.36/jul-to-slf4j-1.7.36.jar:/Users/prats/.m2/repository/jakarta/annotation/jakarta.annotation-api/1.3.5/jakarta.annotation-api-1.3.5.jar:/Users/prats/.m2/repository/org/yaml/snakeyaml/1.30/snakeyaml-1.30.jar:/Users/prats/.m2/repository/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar:/Users/prats/.m2/repository/org/springframework/spring-core/5.3.21/spring-core-5.3.21.jar:/Users/prats/.m2/repository/org/springframework/spring-jcl/5.3.21/spring-jcl-5.3.21.jar:/Users/prats/.m2/repository/io/projectreactor/reactor-core/3.4.19/reactor-core-3.4.19.jar:/Users/prats/.m2/repository/org/reactivestreams/reactive-streams/1.0.4/reactive-streams-1.0.4.jar com.vinsguru.consumer.RedisStreamConsumerApplication

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.1)

2022-12-28 19:11:46.366  INFO 81729 --- [           main] c.v.c.RedisStreamConsumerApplication     : Starting RedisStreamConsumerApplication using Java 11.0.13 on Prateeks-MacBook-Pro.local with PID 81729 (/Users/prats/Documents/workspace/redis-stream/redis-stream-consumer/target/classes started by prateekashtikar in /Users/prats/Documents/workspace/redis-stream)
2022-12-28 19:11:46.372  INFO 81729 --- [           main] c.v.c.RedisStreamConsumerApplication     : No active profile set, falling back to 1 default profile: "default"
2022-12-28 19:11:47.601  INFO 81729 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode
2022-12-28 19:11:47.606  INFO 81729 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data Redis repositories in DEFAULT mode.
2022-12-28 19:11:47.652  INFO 81729 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 26 ms. Found 0 Redis repository interfaces.
2022-12-28 19:11:49.423  INFO 81729 --- [           main] c.v.c.RedisStreamConsumerApplication     : Started RedisStreamConsumerApplication in 4.018 seconds (JVM running for 4.844)
Total Consumed :: 0
Prateeks-MacBook-Pro.local - consumed :Product(name=microservices, price=16.0, category=BOOKS)
Prateeks-MacBook-Pro.local - consumed :Product(name=macbook, price=2517.25, category=ELECTRONICS)
Prateeks-MacBook-Pro.local - consumed :Product(name=vacuum cleaner, price=48.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=speaker, price=65.25, category=ELECTRONICS)
Prateeks-MacBook-Pro.local - consumed :Product(name=face wash, price=13.0, category=COSMETICS)
Prateeks-MacBook-Pro.local - consumed :Product(name=headphone, price=133.25, category=ELECTRONICS)
Prateeks-MacBook-Pro.local - consumed :Product(name=headphone, price=133.25, category=ELECTRONICS)
Prateeks-MacBook-Pro.local - consumed :Product(name=vacuum cleaner, price=48.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=effective java, price=41.0, category=BOOKS)
Prateeks-MacBook-Pro.local - consumed :Product(name=plants, price=9.75, category=OUTDOOR)
Prateeks-MacBook-Pro.local - consumed :Product(name=headphone, price=133.25, category=ELECTRONICS)
Prateeks-MacBook-Pro.local - consumed :Product(name=clean architecture, price=32.0, category=BOOKS)
Prateeks-MacBook-Pro.local - consumed :Product(name=face wash, price=13.0, category=COSMETICS)
Prateeks-MacBook-Pro.local - consumed :Product(name=power tools, price=73.5, category=OUTDOOR)
Prateeks-MacBook-Pro.local - consumed :Product(name=oven, price=500.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=speaker, price=65.25, category=ELECTRONICS)
Prateeks-MacBook-Pro.local - consumed :Product(name=refrigerator, price=1200.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=microservices, price=16.0, category=BOOKS)
Prateeks-MacBook-Pro.local - consumed :Product(name=how to win friends and influence, price=13.0, category=BOOKS)
Prateeks-MacBook-Pro.local - consumed :Product(name=pools, price=111.75, category=OUTDOOR)
Prateeks-MacBook-Pro.local - consumed :Product(name=brush, price=9.5, category=COSMETICS)
Prateeks-MacBook-Pro.local - consumed :Product(name=plants, price=9.75, category=OUTDOOR)
Prateeks-MacBook-Pro.local - consumed :Product(name=heater, price=65.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=makeup mirror, price=17.5, category=COSMETICS)
Prateeks-MacBook-Pro.local - consumed :Product(name=pools, price=111.75, category=OUTDOOR)
Total Consumed :: 25
Prateeks-MacBook-Pro.local - consumed :Product(name=dishwasher, price=125.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=oven, price=500.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=refrigerator, price=1200.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=heater, price=65.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=refrigerator, price=1200.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=refrigerator, price=1200.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=oven, price=500.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=plants, price=9.75, category=OUTDOOR)
Prateeks-MacBook-Pro.local - consumed :Product(name=dishwasher, price=125.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=plants, price=9.75, category=OUTDOOR)
Total Consumed :: 35
Prateeks-MacBook-Pro.local - consumed :Product(name=sony 4k tv, price=999.25, category=ELECTRONICS)
Prateeks-MacBook-Pro.local - consumed :Product(name=oven, price=500.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=refrigerator, price=1200.0, category=APPLIANCES)
Prateeks-MacBook-Pro.local - consumed :Product(name=microservices, price=16.0, category=BOOKS)
Prateeks-MacBook-Pro.local - consumed :Product(name=brush, price=9.5, category=COSMETICS)
Prateeks-MacBook-Pro.local - consumed :Product(name=microservices, price=16.0, category=BOOKS)
Prateeks-MacBook-Pro.local - consumed :Product(name=sony 4k tv, price=999.25, category=ELECTRONICS)
Total Consumed :: 42
```
