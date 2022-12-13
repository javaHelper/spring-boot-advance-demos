# spring webflux validation

- Case-1:

````
curl --location --request POST 'http://localhost:8080/user/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "vins",
    "lastName": "vins",
    "age": 30,
    "email": "abcd@abcd.com"
}'
````

Response:

````
[
    "Country can not be empty"
]
````

---

````
curl --location --request POST 'http://localhost:8080/user/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "vins",
    "lastName": "vins",
    "age": 30,
    "email": "abcd@abcd.com",
    "country" : "US"
}'
````

Response:

````
[
    "SSN can not be empty"
]
````

---

- Correct Request 

````
curl --location --request POST 'http://localhost:8080/user/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "vins",
    "lastName": "vins",
    "age": 30,
    "email": "abcd@abcd.com",
    "country" : "IN"
}'
````