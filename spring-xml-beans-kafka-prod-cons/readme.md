# Exception Handling in Kafka

Start the App, it will automatically publish the message into e-employee topic and consumer will read thge message

Now send the garbage data and will handle the exception and things will not go into endless loop. 

```
kafka-console-producer --broker-list localhost:9092 --topic t-employee
>{"empId":44,"firstName":"Boyce","lastName":"Leuschke","gender":"M"
>{"empId":44,"firstName":"Boyce","lastName":"Leuschke","gender":"M"}
```

- App Logs

```
23:22:51.138 [containerListener-C-1] INFO  o.s.k.l.KafkaMessageListenerContainer - group1: partitions assigned: [t-employee-0]
23:22:51.145 [containerListener-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id, groupId=group1] Setting offset for partition t-employee-0 to the committed offset FetchPosition{offset=11, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[localhost:9092 (id: 1 rack: null)], epoch=6}}
23:22:51.179 [containerListener-C-1] INFO  c.e.kafka.listener.MyMessageListener - ##### Consuming Message ########
23:22:51.179 [containerListener-C-1] INFO  c.e.kafka.listener.MyMessageListener - Topic: t-employee, Partition: 0, Offset: 11, Message # Employee(empId=26, firstName=Christopher, lastName=Mohr, gender=M)
Employee : Employee(empId=26, firstName=Christopher, lastName=Mohr, gender=M)
23:22:51.179 [containerListener-C-1] INFO  c.e.kafka.listener.MyMessageListener - ####################
```

- App logs with junk data

```
23:23:40.992 [containerListener-C-1] ERROR c.e.kafka.serdes.AppJsonDeserializer - ## Exception : Unexpected end-of-input: expected close marker for Object (start marker at [Source: (byte[])"{"empId":44,"firstName":"Boyce","lastName":"Leuschke","gender":"M""; line: 1, column: 1])
 at [Source: (byte[])"{"empId":44,"firstName":"Boyce","lastName":"Leuschke","gender":"M""; line: 1, column: 67]
23:23:41.053 [containerListener-C-1] ERROR o.s.k.listener.DefaultErrorHandler - Backoff FixedBackOff{interval=0, currentAttempts=1, maxAttempts=0} exhausted for t-employee-0@12
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
	at java.base/java.lang.Thread.run(Thread.java:842)
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
Caused by: org.apache.kafka.common.errors.SerializationException: com.fasterxml.jackson.core.io.JsonEOFException: Unexpected end-of-input: expected close marker for Object (start marker at [Source: (byte[])"{"empId":44,"firstName":"Boyce","lastName":"Leuschke","gender":"M""; line: 1, column: 1])
 at [Source: (byte[])"{"empId":44,"firstName":"Boyce","lastName":"Leuschke","gender":"M""; line: 1, column: 67]
	at com.example.kafka.serdes.AppJsonDeserializer.deserialize(AppJsonDeserializer.java:58)
	at org.apache.kafka.common.serialization.Deserializer.deserialize(Deserializer.java:62)
	at org.springframework.kafka.support.serializer.ErrorHandlingDeserializer.deserialize(ErrorHandlingDeserializer.java:215)
	... 14 common frames omitted
Caused by: com.fasterxml.jackson.core.io.JsonEOFException: Unexpected end-of-input: expected close marker for Object (start marker at [Source: (byte[])"{"empId":44,"firstName":"Boyce","lastName":"Leuschke","gender":"M""; line: 1, column: 1])
 at [Source: UNKNOWN; line: 1, column: 67]
	at com.fasterxml.jackson.core.base.ParserMinimalBase._reportInvalidEOF(ParserMinimalBase.java:697)
	at com.fasterxml.jackson.core.base.ParserBase._handleEOF(ParserBase.java:512)
	at com.fasterxml.jackson.core.base.ParserBase._eofAsNextChar(ParserBase.java:529)
	at com.fasterxml.jackson.core.json.UTF8StreamJsonParser._skipWSOrEnd(UTF8StreamJsonParser.java:3103)
	at com.fasterxml.jackson.core.json.UTF8StreamJsonParser.nextFieldName(UTF8StreamJsonParser.java:1040)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.vanillaDeserialize(BeanDeserializer.java:321)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:177)
	at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
	at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:4674)
	at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3690)
	at com.example.kafka.serdes.AppJsonDeserializer.deserialize(AppJsonDeserializer.java:55)
	... 16 common frames omitted
```

Now Again, publish the good record from console

```
23:24:25.701 [containerListener-C-1] INFO  c.e.kafka.listener.MyMessageListener - ##### Consuming Message ########
23:24:25.701 [containerListener-C-1] INFO  c.e.kafka.listener.MyMessageListener - Topic: t-employee, Partition: 0, Offset: 66, Message # Employee(empId=44, firstName=Boyce, lastName=Leuschke, gender=M)
Employee : Employee(empId=44, firstName=Boyce, lastName=Leuschke, gender=M)
23:24:25.701 [containerListener-C-1] INFO  c.e.kafka.listener.MyMessageListener - ####################
```

#

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
    http://www.springframework.org/schema/util
           http://www.springframework.org/schema/util/spring-util.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-3.0.xsd">

    <bean id="employeeProducer" class="com.example.kafka.producer.EmployeeProducer" />

    <bean id="cf" class="org.springframework.kafka.core.DefaultKafkaConsumerFactory">
        <constructor-arg>
            <map>
                <entry key="bootstrap.servers" value="localhost:9092,localhost:9093,localhost:9094"/>
                <entry key="spring.json.trusted.packages" value="*" />
                <entry key="auto.offset.reset" value="latest"/>
                <entry key="group.id" value="group1" />
                <entry key="client.id" value="my-client-id" />
                <entry key="max.poll.records" value="1"/>
                <entry key="key.deserializer" value="org.springframework.kafka.support.serializer.ErrorHandlingDeserializer" />
                <entry key="value.deserializer" value="org.springframework.kafka.support.serializer.ErrorHandlingDeserializer" />

                <entry key="spring.deserializer.key.delegate.class" value="org.apache.kafka.common.serialization.StringDeserializer" />
                <entry key="spring.deserializer.value.delegate.class" value="org.springframework.kafka.support.serializer.JsonDeserializer" />
                <entry key="value.class.name" value="com.example.kafka.model.Employee" />
                <entry key="spring.json.value.default.type" value="com.example.kafka.model.Employee" />
                <entry key="spring.kafka.producer.properties.spring.json.add.type.headers" value="false" />
            </map>
        </constructor-arg>
    </bean>

    <bean id="cp" class="org.springframework.kafka.listener.ContainerProperties">
        <constructor-arg name="topics" value="t-employee"/>
        <property name="groupId" value="group1"/>
        <property name="messageListener" ref="myListener"  />
        <property name="ackMode" value="BATCH" />
    </bean>

    <bean id="containerListener" class="org.springframework.kafka.listener.KafkaMessageListenerContainer">
        <constructor-arg index="0" ref="cf"/>
        <constructor-arg index="1" ref="cp" />
    </bean>

    <bean id="myListener" class="com.example.kafka.listener.MyMessageListener" />
</beans>
```
