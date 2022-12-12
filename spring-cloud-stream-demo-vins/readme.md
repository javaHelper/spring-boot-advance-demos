# Spring Cloud Stream Demo

I used confluent kafka, since attached docker-compose (kafka-producer) was giving some issues.

```sh
confluent local services start
The local commands are intended for a single-node development environment only,
NOT for production usage. https://docs.confluent.io/current/cli/index.html

Using CONFLUENT_CURRENT: /var/folders/kn/4wr9__651l37hckxvnnwt4hh0000gn/T/confluent.467287
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


- Processor logs

<img width="939" alt="Screenshot 2022-12-12 at 2 20 02 PM" src="https://user-images.githubusercontent.com/54174687/207002035-bd4322a1-e7d3-47d6-8e73-1c1268b19957.png">


- Consumer logs

<img width="948" alt="Screenshot 2022-12-12 at 2 20 20 PM" src="https://user-images.githubusercontent.com/54174687/207002059-28ddde0b-a385-437b-ad6b-a239f7e617d8.png">

