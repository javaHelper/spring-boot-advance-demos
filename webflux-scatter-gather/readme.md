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

<img width="695" alt="Screenshot 2023-05-11 at 1 27 56 PM" src="https://github.com/javaHelper/spring-boot-advance-demos/assets/54174687/1f37f215-2f4c-4e8d-8fb0-ff26a42540bd">

# Advantages:
- Reduced network calls & latency
- Aggregator acts like a facade & hides the backend complexity.

Spring WebFlux & Spring WebClient would be a perfect choice here to build this aggregator service for making these calls in a non-blocking way to utilize the system resources efficiently.

# Sample Application:
We are going to build a sample application in which the frontend requires a complete product information.

product details
promotion details
reviews / customer feedbacks.

Let’s assume that these are all being managed by 3 different microservices. So we need an aggregator to receive the request and build the response.



```java
import com.example.demo.client.ProductClient;

import com.example.demo.client.PromotionClient;
import com.example.demo.client.ReviewClient;
import com.example.demo.dto.ProductAggregate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class ProductAggregatorService {
    private final ProductClient productClient;
    private final PromotionClient promotionClient;
    private final ReviewClient reviewClient;

    public Mono<ProductAggregate> getProductById(Integer productId) {
        log.info("Get Products details for ProductId : {}", productId);
        return Mono.zip(
                        this.productClient.getProduct(productId),
                        this.promotionClient.getPromotion(productId),
                        this.reviewClient.getReviews(productId))
                .map(t -> ProductAggregate.create(t.getT1(), t.getT2(), t.getT3()));
    }
}
```

