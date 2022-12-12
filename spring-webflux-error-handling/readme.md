# spring-webflux-error-handling

- You may call 

```
http://localhost:8080/student/1
```

PS: Please change studentId from 1 to 5.

- You may also call few times untill you see the exception message

```
http://localhost:8080/student/all
```

```
{
    "errorCode": 100,
    "message": "Unable to fetch students"
}
```