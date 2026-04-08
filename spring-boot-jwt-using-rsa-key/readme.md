#

# 1. Generate a 2048-bit RSA private key
openssl genrsa -out keypair.pem 2048

# 2. Extract the public key from the key pair
openssl rsa -in keypair.pem -pubout -out public.pem

# 3. Convert the private key to PKCS#8 format
openssl pkcs8 -in keypair.pem -topk8 -nocrypt -inform PEM -outform PEM -out private.pem

# 

````curl
curl --request POST \
  --url http://localhost:8080/auth/register \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=A7626957F7113AD39E98A5F4C427A6DF \
  --data '{
	"username": "john",
	"password": "secret"
}'
````

#Response

````
Customer registered successfully
````

````
curl --request POST \
  --url http://localhost:8080/auth/login \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=A7626957F7113AD39E98A5F4C427A6DF \
  --data '{
	"username": "john",
	"password": "secret"
}'
````

````
{
	"token": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiam9obiIsImV4cCI6MTc3NTY3NzU5MywiaWF0IjoxNzc1NjczOTkzLCJzY29wZSI6IlJPTEVfVVNFUiJ9.FSF0OV1sbEyY1RzKOfcCgroaj4vrmTkOAADVLEI6hnGdPDVmO8z_Q7q8sg8otpPUgqDLDo8EUojjIhy6J92fx96h8tflbIvXcQKmVd3WkUNqzK7XT_HrD7Ns_tzmNL1rXWlol-EIf_UJ-puQ483MgnTITPBv46O1_3hE8qyhDfsJ-AAb8g34_yKPQK1HBeP2pEWhttSb0RLG7eBPFZ_4eec3J5K8Ll5Z6oZVm_xRLd3QH_m2bMocFQmpHfcA7ukvBR_iqf2VdvJKGQ2YMNx0IqgrHt59ioXnJ1Hpo2uL6OE6E3DhIr9jvfSFthXBIuXrTT6qs25Z5m4gYT3rY_Py0g"
}
````



````
curl --request GET \
  --url http://localhost:8080/profile \
  --header 'Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiam9obiIsImV4cCI6MTc3NTY3NzU5MywiaWF0IjoxNzc1NjczOTkzLCJzY29wZSI6IlJPTEVfVVNFUiJ9.FSF0OV1sbEyY1RzKOfcCgroaj4vrmTkOAADVLEI6hnGdPDVmO8z_Q7q8sg8otpPUgqDLDo8EUojjIhy6J92fx96h8tflbIvXcQKmVd3WkUNqzK7XT_HrD7Ns_tzmNL1rXWlol-EIf_UJ-puQ483MgnTITPBv46O1_3hE8qyhDfsJ-AAb8g34_yKPQK1HBeP2pEWhttSb0RLG7eBPFZ_4eec3J5K8Ll5Z6oZVm_xRLd3QH_m2bMocFQmpHfcA7ukvBR_iqf2VdvJKGQ2YMNx0IqgrHt59ioXnJ1Hpo2uL6OE6E3DhIr9jvfSFthXBIuXrTT6qs25Z5m4gYT3rY_Py0g' \
  --cookie JSESSIONID=A7626957F7113AD39E98A5F4C427A6DF
````

```
Welcome john! Your authorities: ROLE_USER
```
