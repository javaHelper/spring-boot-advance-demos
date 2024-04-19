# NOT RECOMMANDE APPROACH

https://stackoverflow.com/questions/78348160/how-to-handle-the-exception-at-consumer-ion-spring

- This example is developed purely using Spring XML

Please make sure you kafka/confluent kafka is up and running

```
kafka-console-producer --broker-list localhost:9092 --topic test-topic
>Hello Spring XML
>Hello from Spring Boot 
>Hello Reactjs 
>
```

- App Logs

```
20:02:08.309 [noBeanNameSet-0-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=myClientId-0, groupId=my-group] Setting offset for partition test-topic-0 to the committed offset FetchPosition{offset=31, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[localhost:9092 (id: 1 rack: null)], epoch=0}}
20:02:45.906 [noBeanNameSet-0-C-1] INFO  c.e.kafka.listener.MyMessageListener - ##### Consuming Message ########
20:02:45.907 [noBeanNameSet-0-C-1] INFO  c.e.kafka.listener.MyMessageListener - Message # Hello from Spring Boot 
20:02:45.907 [noBeanNameSet-0-C-1] INFO  c.e.kafka.listener.MyMessageListener - ####################
20:03:00.414 [noBeanNameSet-0-C-1] INFO  c.e.kafka.listener.MyMessageListener - ##### Consuming Message ########
20:03:00.415 [noBeanNameSet-0-C-1] INFO  c.e.kafka.listener.MyMessageListener - Message # Hello Reactjs 
20:03:00.415 [noBeanNameSet-0-C-1] INFO  c.e.kafka.listener.MyMessageListener - ####################
```
