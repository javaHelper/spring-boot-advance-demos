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

<img width="760" alt="Screenshot 2023-08-30 at 11 13 13 PM" src="https://github.com/javaHelper/spring-boot-advance-demos/assets/54174687/9756a8e2-5f5c-4e20-a2b2-e52188a1539d">
