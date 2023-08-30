# Server Sent Event 

From Browser hit: http://localhost:8080/product/stream

Execute Below twice:

````json
curl --location 'http://localhost:8080/product' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=C4EF506C5FCFE6D594B232BC6CA59E0D' \
--data '{
    "productId": 1,
    "productName": "Apple Laptop",
    "color": "RED",
    "promotionCode": "STAR10",
    "price": 1000
}'
````