# Spring Boot RabbitMQ example 

<img width="1060" alt="Screenshot 2023-06-02 at 5 39 53 PM" src="https://github.com/javaHelper/spring-boot-advance-demos/assets/54174687/0c3acaf9-91f4-40e9-a01f-f1fb4adf897b">

---------------

<img width="945" alt="Screenshot 2023-06-02 at 5 39 07 PM" src="https://github.com/javaHelper/spring-boot-advance-demos/assets/54174687/bea8fbbd-d8ac-4595-9cef-5d09f3ac9dce">

----------------

<img width="1454" alt="Screenshot 2023-06-02 at 5 38 56 PM" src="https://github.com/javaHelper/spring-boot-advance-demos/assets/54174687/59ac7053-1ad8-4461-84e0-aa4c423d7fd0">

-----------------

# Setup RabbitMQ

```
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.10-management
```


```json
curl --location 'http://localhost:7071/order/taj' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Pizza",
    "qty": 10,
    "price": 10.99
}'
```