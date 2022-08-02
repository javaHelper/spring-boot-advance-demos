# Spring Boot - Spring Security + JWT

# GET TOken

```sh
curl --location --request POST 'http://localhost:8080/authenticate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userName": "john",
    "password": "doe"
}'
```

Response:

```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwiaWF0IjoxNjU5NDI4NzE4LCJleHAiOjE2NTk0NjQ3MTh9.WT_C0dBLSqyXDoJosI4mHeEoPrVvPthhYhapn8iXyHw
```

------

# Hit the endpoint

- Note if we dont use token we should/must get 403 forbidden.

```sh
curl --location --request GET 'http://localhost:8080/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwiaWF0IjoxNjU5NDI4NzE4LCJleHAiOjE2NTk0NjQ3MTh9.WT_C0dBLSqyXDoJosI4mHeEoPrVvPthhYhapn8iXyHw'
```

Response:

```
Welcome back Prateek !!
```
