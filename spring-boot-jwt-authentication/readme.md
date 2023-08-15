# Practical Bootcamp JWT Authentication with Java and Spring Boot

# Register the User

```
curl --location 'http://localhost:8080/api/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "admin",
    "password": "admin",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@gmail.com",
    "phone": "6767676888787",
    "roles": [
        {
            "id": 100
        }
    ]
}'
```

Response:

```
{
    "id": 33,
    "username": "admin",
    "password": "$2a$10$Bq14VP2pfIhwuREJgHU/qOjYQz1I0Tw1s0mUe2slCADnis5A8x1PG",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@gmail.com",
    "phone": "6767676888787",
    "roles": [],
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true,
    "authorities": [],
    "enabled": true
}
```

# Login User

```
curl --location 'http://localhost:8080/api/login' \
--header 'Content-Type: application/json' \
--data '{
    "userName": "admin",
    "password": "admin"
}'
```

Response:

```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY5MjExOTY4MCwiaWF0IjoxNjkyMTE5MzgwfQ.FRCKd8Xj1wmXDp23VrJZgYNjimM0Hm6kyJnyhzrVf7M"
}
```

# Get the current User


```
curl --location 'http://localhost:8080/api/currentUser' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY5MjExOTY4MCwiaWF0IjoxNjkyMTE5MzgwfQ.FRCKd8Xj1wmXDp23VrJZgYNjimM0Hm6kyJnyhzrVf7M'
```

Response:

```
{
    "id": 33,
    "username": "admin",
    "password": "$2a$10$Bq14VP2pfIhwuREJgHU/qOjYQz1I0Tw1s0mUe2slCADnis5A8x1PG",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@gmail.com",
    "phone": "6767676888787",
    "roles": [],
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true,
    "authorities": [],
    "enabled": true
}
```

# Get All Roles


```
curl --location 'http://localhost:8080/api/roles' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY5MjExOTY4MCwiaWF0IjoxNjkyMTE5MzgwfQ.FRCKd8Xj1wmXDp23VrJZgYNjimM0Hm6kyJnyhzrVf7M'
```
