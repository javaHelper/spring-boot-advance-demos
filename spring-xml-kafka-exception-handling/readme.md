# 

- Start the confluent server

```
confluent local services start
A minor version update is available for confluent from (current: v3.48.0, latest: v3.63.0).
To view release notes and install the update, please run `confluent update`.

The local commands are intended for a single-node development environment only, NOT for production usage. See more: https://docs.confluent.io/current/cli/index.html
As of Confluent Platform 8.0, Java 8 will no longer be supported.

Using CONFLUENT_CURRENT: /var/folders/kn/4wr9__651l37hckxvnnwt4hh0000gn/T/confluent.706708
Starting ZooKeeper
ZooKeeper is [UP]
Starting Kafka
Kafka is [UP]
Starting Schema Registry
Schema Registry is [UP]
Starting Kafka REST
Kafka REST is [UP]
Starting Connect
Connect is [UP]
Starting ksqlDB Server
ksqlDB Server is [UP]
Starting Control Center
Control Center is [UP]
```


```
kafka-console-producer --bootstrap-server localhost:9092 --topic t-employee
>{"empId":12,"firstName":"Nikita","lastName":"Bergstrom","gender":"M" 
>{"empId":12,"firstName":"Nikita","lastName":"Bergstrom","gender":"M"}    
>
```

- App Logs

```

12:43:05.723 [main] INFO  o.a.k.clients.producer.KafkaProducer - [Producer clientId=my-client-id] Instantiated an idempotent producer.
12:43:05.757 [main] INFO  o.a.kafka.common.utils.AppInfoParser - Kafka version: 3.6.2
12:43:05.757 [main] INFO  o.a.kafka.common.utils.AppInfoParser - Kafka commitId: c4deed513057c94e
12:43:05.757 [main] INFO  o.a.kafka.common.utils.AppInfoParser - Kafka startTimeMs: 1717657985756
12:43:05.883 [containerListener-C-1] WARN  o.apache.kafka.clients.NetworkClient - [Consumer clientId=my-client-id, groupId=group1] Error while fetching metadata with correlation id 2 : {t-employee=UNKNOWN_TOPIC_OR_PARTITION}
12:43:05.885 [containerListener-C-1] INFO  org.apache.kafka.clients.Metadata - [Consumer clientId=my-client-id, groupId=group1] Cluster ID: tmrlTWDlS3anvEdCbevPog
12:43:05.887 [containerListener-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id, groupId=group1] Discovered group coordinator localhost:9092 (id: 2147483647 rack: null)
12:43:05.889 [containerListener-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id, groupId=group1] (Re-)joining group
12:43:05.907 [containerListener-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id, groupId=group1] Request joining group due to: need to re-join with the given member-id: my-client-id-4bdd151a-b62f-4207-9045-10b5ec8fbf2c
12:43:05.907 [containerListener-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id, groupId=group1] Request joining group due to: rebalance failed due to 'The group member needs to have a valid member id before actually entering a consumer group.' (MemberIdRequiredException)
12:43:05.907 [containerListener-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id, groupId=group1] (Re-)joining group
12:43:05.916 [containerListener-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id, groupId=group1] Successfully joined group with generation Generation{generationId=1, memberId='my-client-id-4bdd151a-b62f-4207-9045-10b5ec8fbf2c', protocol='range'}
12:43:05.918 [kafka-producer-network-thread | my-client-id] WARN  o.apache.kafka.clients.NetworkClient - [Producer clientId=my-client-id] Error while fetching metadata with correlation id 1 : {t-employee=LEADER_NOT_AVAILABLE}
12:43:05.918 [kafka-producer-network-thread | my-client-id] INFO  org.apache.kafka.clients.Metadata - [Producer clientId=my-client-id] Cluster ID: tmrlTWDlS3anvEdCbevPog
12:43:05.920 [kafka-producer-network-thread | my-client-id] INFO  o.a.k.c.p.i.TransactionManager - [Producer clientId=my-client-id] ProducerId set to 16 with epoch 0
12:43:06.019 [containerListener-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id, groupId=group1] Finished assignment for group at generation 1: {my-client-id-4bdd151a-b62f-4207-9045-10b5ec8fbf2c=Assignment(partitions=[t-employee-0])}
12:43:06.032 [containerListener-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id, groupId=group1] Successfully synced group in generation Generation{generationId=1, memberId='my-client-id-4bdd151a-b62f-4207-9045-10b5ec8fbf2c', protocol='range'}
12:43:06.034 [containerListener-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id, groupId=group1] Notifying assignor about the new Assignment(partitions=[t-employee-0])
12:43:06.039 [containerListener-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id, groupId=group1] Adding newly assigned partitions: t-employee-0
12:43:06.048 [containerListener-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id, groupId=group1] Found no committed offset for partition t-employee-0
12:43:06.069 [containerListener-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id, groupId=group1] Found no committed offset for partition t-employee-0
>> Producer send the message : Employee(empId=12, firstName=Nikita, lastName=Bergstrom, gender=M)
12:43:06.094 [containerListener-C-1] INFO  o.a.k.c.c.i.SubscriptionState - [Consumer clientId=my-client-id, groupId=group1] Resetting offset for partition t-employee-0 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[localhost:9092 (id: 0 rack: null)], epoch=0}}.
12:43:06.110 [containerListener-C-1] INFO  o.s.k.l.KafkaMessageListenerContainer - group1: partitions assigned: [t-employee-0]
12:43:06.144 [containerListener-C-1] INFO  c.e.kafka.listener.MyMessageListener - ##### Consuming Message ########
12:43:06.144 [containerListener-C-1] INFO  c.e.kafka.listener.MyMessageListener - Topic: t-employee, Partition: 0, Offset: 0, Message # Employee(empId=12, firstName=Nikita, lastName=Bergstrom, gender=M)
Employee : Employee(empId=12, firstName=Nikita, lastName=Bergstrom, gender=M)
12:43:06.144 [containerListener-C-1] INFO  c.e.kafka.listener.MyMessageListener - ####################
12:45:05.770 [containerListener-C-1] ERROR o.s.k.listener.DefaultErrorHandler - Backoff FixedBackOff{interval=0, currentAttempts=1, maxAttempts=0} exhausted for t-employee-0@1
org.springframework.kafka.listener.ListenerExecutionFailedException: Listener failed
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2961)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.checkDeser(KafkaMessageListenerContainer.java:3009)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2861)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.lambda$doInvokeRecordListener$55(KafkaMessageListenerContainer.java:2783)
	at io.micrometer.observation.Observation.observe(Observation.java:562)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2781)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:2631)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:2517)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:2155)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeIfHaveRecords(KafkaMessageListenerContainer.java:1495)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1460)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1330)
	at java.base/java.util.concurrent.CompletableFuture$AsyncRun.run(CompletableFuture.java:1804)
	at java.base/java.lang.Thread.run(Thread.java:833)
Caused by: org.springframework.kafka.support.serializer.DeserializationException: failed to deserialize
	at org.springframework.kafka.support.serializer.SerializationUtils.deserializationException(SerializationUtils.java:158)
	at org.springframework.kafka.support.serializer.ErrorHandlingDeserializer.deserialize(ErrorHandlingDeserializer.java:218)
	at org.apache.kafka.common.serialization.Deserializer.deserialize(Deserializer.java:73)
	at org.apache.kafka.clients.consumer.internals.CompletedFetch.parseRecord(CompletedFetch.java:300)
	at org.apache.kafka.clients.consumer.internals.CompletedFetch.fetchRecords(CompletedFetch.java:263)
	at org.apache.kafka.clients.consumer.internals.AbstractFetch.fetchRecords(AbstractFetch.java:340)
	at org.apache.kafka.clients.consumer.internals.AbstractFetch.collectFetch(AbstractFetch.java:306)
	at org.apache.kafka.clients.consumer.KafkaConsumer.pollForFetches(KafkaConsumer.java:1262)
	at org.apache.kafka.clients.consumer.KafkaConsumer.poll(KafkaConsumer.java:1186)
	at org.apache.kafka.clients.consumer.KafkaConsumer.poll(KafkaConsumer.java:1159)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollConsumer(KafkaMessageListenerContainer.java:1666)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doPoll(KafkaMessageListenerContainer.java:1641)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1439)
	... 3 common frames omitted
Caused by: org.apache.kafka.common.errors.SerializationException: Can't deserialize data  from topic [t-employee]
	at org.springframework.kafka.support.serializer.JsonDeserializer.deserialize(JsonDeserializer.java:588)
	at org.springframework.kafka.support.serializer.ErrorHandlingDeserializer.deserialize(ErrorHandlingDeserializer.java:215)
	... 14 common frames omitted
Caused by: com.fasterxml.jackson.core.io.JsonEOFException: Unexpected end-of-input: expected close marker for Object (start marker at [Source: (byte[])"{"empId":12,"firstName":"Nikita","lastName":"Bergstrom","gender":"M""; line: 1, column: 1])
 at [Source: UNKNOWN; line: 1, column: 69]
	at com.fasterxml.jackson.core.base.ParserMinimalBase._reportInvalidEOF(ParserMinimalBase.java:697)
	at com.fasterxml.jackson.core.base.ParserBase._handleEOF(ParserBase.java:512)
	at com.fasterxml.jackson.core.base.ParserBase._eofAsNextChar(ParserBase.java:529)
	at com.fasterxml.jackson.core.json.UTF8StreamJsonParser._skipWSOrEnd(UTF8StreamJsonParser.java:3103)
	at com.fasterxml.jackson.core.json.UTF8StreamJsonParser.nextFieldName(UTF8StreamJsonParser.java:1040)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:399)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
	at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
	at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2051)
	at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1529)
	at org.springframework.kafka.support.serializer.JsonDeserializer.deserialize(JsonDeserializer.java:585)
	... 15 common frames omitted
12:45:25.176 [containerListener-C-1] INFO  c.e.kafka.listener.MyMessageListener - ##### Consuming Message ########
12:45:25.177 [containerListener-C-1] INFO  c.e.kafka.listener.MyMessageListener - Topic: t-employee, Partition: 0, Offset: 2, Message # Employee(empId=12, firstName=Nikita, lastName=Bergstrom, gender=M)
Employee : Employee(empId=12, firstName=Nikita, lastName=Bergstrom, gender=M)
12:45:25.181 [containerListener-C-1] INFO  c.e.kafka.listener.MyMessageListener - ####################
```
