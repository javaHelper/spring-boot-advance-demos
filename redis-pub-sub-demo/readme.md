# redis pub sub demo

# Redis PubSub:

PubSub is an asynchronous messaging model for service-to-service communication in Microservices architecture. That is, a service (Publisher) instead of sending a message to a specific recipient, it publishes the message to a Topic/Channel, through which interested parties (Subscribers) receive the message.

<img width="596" alt="Screenshot 2022-12-28 at 7 39 55 PM" src="https://user-images.githubusercontent.com/54174687/209824706-1510733f-0d83-4779-a642-cf3c582796d7.png">


|  Advantages 	              |   	                                                                                                            | 
|---	                        |---	                                                                                                            |
| One-To-Many Communication  	|A Publisher can publish a single message where N number of subscribers can receive and react to the message.    	|   	
| Loose Coupling  	          |Services are not tightly coupled. Any service can consume / ignore the message   	                              |   	
| Better Performance          | Publisher does not have to call N number of services. Instead it just publishes a message into a topic.         |
|                              | It does not have to have any knowledge on the subscribers. It is not blocked.   	                              |   	

<img width="1146" alt="Screenshot 2022-12-12 at 3 35 53 PM" src="https://user-images.githubusercontent.com/54174687/207018180-57ae2dad-da4e-4d49-b845-3a2851b46c91.png">
