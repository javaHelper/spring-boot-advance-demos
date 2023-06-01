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
````shell
Hibernate: 
    select
        product0_.id as id1_0_0_,
        product0_.description as descript2_0_0_,
        product0_.name as name3_0_0_,
        product0_.price as price4_0_0_,
        product0_.productType as productt5_0_0_ 
    from
        Product product0_ 
    where
        product0_.id=?
Hibernate: 
    update
        Product 
    set
        description=?,
        name=? 
    where
        id=?
        
```
