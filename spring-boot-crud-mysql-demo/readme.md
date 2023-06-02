#

# Add Product

````
curl --location 'http://localhost:9191/product/addProduct' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Lancer Shoes",
    "qty": 1,
    "price": 100.0
}'
````

````
curl --location 'http://localhost:9191/product/addProduct' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Mac Book Pro",
    "qty": 1,
    "price": 1000.0
}'
````

````
curl --location 'http://localhost:9191/product/addProduct' \
--header 'Content-Type: application/json' \
--data '{
    "name": "HeadPhones",
    "qty": 1,
    "price": 11.0
}'
````

# Find All Products

http://localhost:9191/product/products

# Find Product By Id 

http://localhost:9191/product/productById/2

# Find Product By Name

http://localhost:9191/product/product/Mac Book Pro

# Update Product

````
curl --location --request PUT 'http://localhost:9191/product/update' \
--header 'Content-Type: application/json' \
--data '{
    "id": 3,
    "name": "HeadPhones",
    "qty": 3,
    "price": 33.0
}'
````

# Delete Product

http://localhost:9191/product/delete/3