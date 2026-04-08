# token

````
curl --request POST \
  --url http://localhost:8080/api/authenticate \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/12.5.0' \
  --cookie JSESSIONID=A7626957F7113AD39E98A5F4C427A6DF \
  --data '{
	"username": "john",
	"password": "password"
}'
````
# Response

````
{
	"jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwiaWF0IjoxNzc1NjcyMDg4LCJleHAiOjE3NzU2NzIxNDh9.PDJKN_R3hgee2m9KG7Y4librjd1nBMtS4fz5QjctjlQ"
}
````



````
curl --request GET \
  --url http://localhost:8080/customer \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwiaWF0IjoxNzc1NjcyMDg4LCJleHAiOjE3NzU2NzIxNDh9.PDJKN_R3hgee2m9KG7Y4librjd1nBMtS4fz5QjctjlQ' \
  --header 'User-Agent: insomnia/12.5.0' \
  --cookie JSESSIONID=A7626957F7113AD39E98A5F4C427A6DF
````