# Circuit Breaker Pattern (CB pattern)

HTTP GET: http://localhost:8080/product/1

Response:

```json
{
    "productId": 1,
    "description": "Blood On The Dance Floor",
    "price": 12.45,
    "productRating": {
        "avgRating": 4.5,
        "reviews": [
            {
                "userFirstname": "vins",
                "userLastname": "guru",
                "productId": 1,
                "rating": 5,
                "comment": "excellent"
            },
            {
                "userFirstname": "marshall",
                "userLastname": "mathers",
                "productId": 1,
                "rating": 4,
                "comment": "decent"
            }
        ]
    }
}
```

------

- When there is a fallback

```json
{
    "productId": 1,
    "description": "Blood On The Dance Floor",
    "price": 12.45,
    "productRating": {
        "avgRating": 0.0,
        "reviews": []
    }
}
```

<img width="1263" alt="Screenshot 2022-12-13 at 5 49 18 PM" src="https://user-images.githubusercontent.com/54174687/207316290-a3a86f9d-7988-48dc-9365-e6e7a820001f.png">


