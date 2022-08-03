# Spring Boot RabbitMQ example!

# Post Message and Consume it

```sh
curl --location --request POST 'http://localhost:8080/order/T-Point' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Veg Pizza",
    "qty": 3,
    "price": 456
}'
```
Response:

```
----------------------------
Message received from queue : OrderStatus(order=Order(orderId=b6abff32-e430-461a-8261-b5ecfdd382cc, name=Veg Pizza, qty=3, price=456.0), status=PROCESS, message=order placed successfully in T-Point)
```


<img width="1291" alt="Screenshot 2022-08-03 at 2 55 34 PM" src="https://user-images.githubusercontent.com/54174687/182574328-4309d6fc-5464-4228-9dcc-b69e6a9028f8.png">

<img width="1007" alt="Screenshot 2022-08-03 at 2 55 51 PM" src="https://user-images.githubusercontent.com/54174687/182574365-4ebbc93d-7520-4572-ba3c-9b4996251427.png">

<img width="1363" alt="Screenshot 2022-08-03 at 2 56 25 PM" src="https://user-images.githubusercontent.com/54174687/182574377-b5ef886b-df6f-4acc-a167-736fb3ad6ed0.png">

