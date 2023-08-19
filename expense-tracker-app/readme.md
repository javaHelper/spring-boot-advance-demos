# Expense Tracker App

https://tcsglobal.udemy.com/course/build-production-ready-rest-api-with-spring-boot-expense-manager-api/learn/lecture/30427946#overview

1) Create User - you can also perform the validations

````
curl --location 'http://localhost:8080/api/v1/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "john",
    "email": "john.doe@gmail.com",
    "password": "12345"
}'
````

Response:

````
{
    "id": 1,
    "name": "john",
    "email": "john.doe@gmail.com",
    "age": 0,
    "createdAt": "2023-08-18T17:15:54.456+00:00",
    "updatedAt": "2023-08-18T17:15:54.456+00:00"
}
````

-----

2) Login User

````
curl --location 'http://localhost:8080/api/v1/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "john.doe@gmail.com",
    "password": "12345"
}'
````

Response:

````
{
    "jwtToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huLmRvZUBnbWFpbC5jb20iLCJBcHBsaWNhdGlvbklkIjoiRXhwZW5zZS1UcmFja2VyLUFwcCIsImV4cCI6MTY5MjQ0MDAzNywiaWF0IjoxNjkyNDIyMDM3fQ.x8vmDIvk_znMNuqxGVqJojviKTahC_G8Xds8SVdZPvHDQQDc5-D9MW4VyfPIgvlomoXIOxQcuQSlZodKUDwDQA"
}
````

-----

3) Get All expenses

````
curl --location 'http://localhost:8080/api/v1/expenses' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huLmRvZUBnbWFpbC5jb20iLCJBcHBsaWNhdGlvbklkIjoiRXhwZW5zZS1UcmFja2VyLUFwcCIsImV4cCI6MTY5MjQ0MDAzNywiaWF0IjoxNjkyNDIyMDM3fQ.x8vmDIvk_znMNuqxGVqJojviKTahC_G8Xds8SVdZPvHDQQDc5-D9MW4VyfPIgvlomoXIOxQcuQSlZodKUDwDQA'
````

Response:

````
[
    {
        "id": 1,
        "name": "Water Bills",
        "description": "Water Bill",
        "amount": 10000.00,
        "category": "Bill",
        "date": "2023-08-19",
        "createdAt": "2023-08-19T05:13:45.490+00:00",
        "updatedAt": "2023-08-19T05:13:45.490+00:00"
    }
]
````

4) Get Expense By Id

````
curl --location 'http://localhost:8080/api/v1/expenses/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huLmRvZUBnbWFpbC5jb20iLCJBcHBsaWNhdGlvbklkIjoiRXhwZW5zZS1UcmFja2VyLUFwcCIsImV4cCI6MTY5MjQ0MDAzNywiaWF0IjoxNjkyNDIyMDM3fQ.x8vmDIvk_znMNuqxGVqJojviKTahC_G8Xds8SVdZPvHDQQDc5-D9MW4VyfPIgvlomoXIOxQcuQSlZodKUDwDQA'
````

Response:
````
{
    "id": 1,
    "name": "Water Bills",
    "description": "Water Bill",
    "amount": 10000.00,
    "category": "Bill",
    "date": "2023-08-19",
    "createdAt": "2023-08-19T05:13:45.490+00:00",
    "updatedAt": "2023-08-19T05:13:45.490+00:00"
}
````