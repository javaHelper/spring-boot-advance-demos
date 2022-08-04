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
