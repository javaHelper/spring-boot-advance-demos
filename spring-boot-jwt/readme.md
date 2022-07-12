# Spring Boot JWT

- Make a POST request to `/users/signin` with the default admin user we programatically created to get a valid JWT token

```sh
$ curl -X POST 'http://localhost:8080/users/signin?username=admin&password=admin'
```
Response:

```sh
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTY1NzY0MzAyNiwiZXhwIjoxNjU3NjQ2NjI2fQ.95rFZ9tsyNLN3ZFCVoN12-xjAv0sUf3OfJZpRscQlro
```


- Add the JWT token as a Header parameter and make the initial GET request to `/users/me` again.

```sh
curl --location --request GET 'http://localhost:8080/users/me' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTY1NzY0MzAyNiwiZXhwIjoxNjU3NjQ2NjI2fQ.95rFZ9tsyNLN3ZFCVoN12-xjAv0sUf3OfJZpRscQlro'
```

Response:

```sh
{
    "id": 1,
    "username": "admin",
    "email": "admin@email.com",
    "appUserRoles": [
        "ROLE_ADMIN"
    ]
}
```
