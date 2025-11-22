#

````shell
curl --location --request PUT 'http://localhost:9191/products/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Sony LED TV",
    "price": 10.0,
    "description": "Sony best LED TV",
    "productType":"Digital"
}'
````

Response:

````shell
{
    "id": 1,
    "name": "Sony LED TV",
    "price": 10.0,
    "description": "Sony best LED TV",
    "productType": "Digital"
}
````

# Query
````logs
Hibernate: select p1_0.id,p1_0.description,p1_0.name,p1_0.price,p1_0.productType from Product p1_0 where p1_0.id=?
Hibernate: update Product set description=?,name=? where id=?
````