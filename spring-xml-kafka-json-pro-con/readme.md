#  Spring XML to publish & subscribe Json Messages

- This uses purely Spring XML based approach to send the JSON payload and consume JSON payload

```
20:34:21.597 [noBeanNameSet-0-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id-0, groupId=group1] (Re-)joining group
>> Producer send the message : Employee(empId=67, firstName=Ellena, lastName=Larson, gender=M)
20:35:00.936 [noBeanNameSet-0-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id-0, groupId=group1] Successfully joined group with generation Generation{generationId=6, memberId='my-client-id-0-e72fd22d-58fa-4498-a747-7c385e7dec6c', protocol='range'}
20:35:00.945 [noBeanNameSet-0-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id-0, groupId=group1] Finished assignment for group at generation 6: {my-client-id-0-e72fd22d-58fa-4498-a747-7c385e7dec6c=Assignment(partitions=[t-employee-0])}
20:35:00.954 [noBeanNameSet-0-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id-0, groupId=group1] Successfully synced group in generation Generation{generationId=6, memberId='my-client-id-0-e72fd22d-58fa-4498-a747-7c385e7dec6c', protocol='range'}
20:35:00.954 [noBeanNameSet-0-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id-0, groupId=group1] Notifying assignor about the new Assignment(partitions=[t-employee-0])
20:35:00.956 [noBeanNameSet-0-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id-0, groupId=group1] Adding newly assigned partitions: t-employee-0
20:35:00.969 [noBeanNameSet-0-C-1] INFO  o.s.k.l.ConcurrentMessageListenerContainer - group1: partitions assigned: [t-employee-0]
20:35:00.980 [noBeanNameSet-0-C-1] INFO  o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=my-client-id-0, groupId=group1] Setting offset for partition t-employee-0 to the committed offset FetchPosition{offset=1, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[localhost:9092 (id: 1 rack: null)], epoch=0}}
20:35:01.012 [noBeanNameSet-0-C-1] INFO  c.e.kafka.listener.MyMessageListener - ##### Consuming Message ########
20:35:01.012 [noBeanNameSet-0-C-1] INFO  c.e.kafka.listener.MyMessageListener - Message # {"empId":67,"firstName":"Ellena","lastName":"Larson","gender":"M"}
20:35:01.012 [noBeanNameSet-0-C-1] INFO  c.e.kafka.listener.MyMessageListener - ####################
```

# This code doesn't works when producer sends junk data, code goes into endless loop.
