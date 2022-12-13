# Timeout Pattern

Http GET: http://localhost:8080/product/1

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

---

- When downstream system is down

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

<img width="1264" alt="Screenshot 2022-12-13 at 6 04 42 PM" src="https://user-images.githubusercontent.com/54174687/207320120-6728f139-116f-4a97-af3f-f596500477b9.png">
