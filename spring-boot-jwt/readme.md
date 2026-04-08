# Spring Boot JWT token Demo

# Create / Register User

```bash
curl --request POST \
  --url http://localhost:8080/api/auth/signup \
  --header 'Content-Type: application/json' \
  --data '{
	"username": "testuser",
	"password": "testpass"
}'
```

# Sign In User

```bash
curl --request POST \
  --url http://localhost:8080/api/auth/signin \
  --header 'Content-Type: application/json' \
  --data '{
	"username": "testuser",
	"password": "testpass"
}'
```

# Response

```json
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImlhdCI6MTc3NTY2MjYwNSwiZXhwIjoxNzc1NjY2MjA1fQ.bktdQu4AL9kysK-FKnigCeD_V-TInVeZ-ODLYmJB6f0
```

# Access Not Protected Resource

```bash
curl --request GET \
  --url http://localhost:8080/api/test/all
```

# Access Protected Resource

```bash
curl --request GET \
  --url http://localhost:8080/api/test/user \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImlhdCI6MTc3NTY2MjYwNSwiZXhwIjoxNzc1NjY2MjA1fQ.bktdQu4AL9kysK-FKnigCeD_V-TInVeZ-ODLYmJB6f0'
```