#

- Get All Reviews: `http://localhost:3000/reviews`

- Get All Promotions: `http://localhost:3000/promotions`

- Get All Products: `http://localhost:3000/products`

- Get aggregated Products: `http://localhost:8080/product/1`

Response:

```sh
{
    "product": {
        "id": "1",
        "description": "iphone",
        "category": "electronics"
    },
    "promotion": {
        "type": "christmas sale",
        "discount": 12.0,
        "endDate": "2022-12-26"
    },
    "reviews": [
        {
            "user": "sam",
            "rating": 4,
            "comment": "it is good"
        },
        {
            "user": "mike",
            "rating": 5,
            "comment": "i love my phone. it works great"
        }
    ]
}
```
-----------

# Overview:
In this tutorial, Let’s develop a simple application to demonstrate Spring WebFlux Aggregation (aka Gateway Aggregation) to send requests to multiple Microservices and gather responses from them to aggregate into a single response.

# Spring WebFlux Aggregation:
In a Microservice architecture, we will have N number of services and each service has its own responsibilities & databases. Data would have been distributed among them. For ex:

- user-service: It is responsible for all the user management.
- order-service: It is responsible for managing customer’s orders.

Let’s say we have a requirement in which user profile page should show user information along with user’s orders. To do this, the client/frontend might have to call these 2 microservices to build the page. If you have something like a dashboard page, the frontend might have to call multiple microservices. Usually it is NOT a preferred approach to let the frontend to make multiple calls. Instead we could have another microservice which acts like an aggregator whose main responsibility is to receive the request from the frontend and gather all the  required information from multiple microservices, build the combined response and send it to the frontend as shown here.


