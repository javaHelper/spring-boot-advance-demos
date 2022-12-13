#

HTTP GET: http://localhost:8080/product/1


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

when fallback is called 

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

<img width="1260" alt="Screenshot 2022-12-13 at 6 18 16 PM" src="https://user-images.githubusercontent.com/54174687/207322854-6721302b-90ad-45f9-9694-595f93644741.png">
