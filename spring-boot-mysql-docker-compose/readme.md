# Spring Boot MYSQL Docker Compose

Please run the below command 

- Build docker-compose file
  
**`docker-compose build`**

- Run docker compose to make app and db live

**`docker-compose up`**

Hit the below endpoints

```sh
curl --location 'http://localhost:8080/api/products/add' \
--header 'Content-Type: application/json' \
--data '{
    "productName": "Cricket Bat",
    "productPrice": "999"
}'
```

Response:
```json
{
    "id": 2,
    "productName": "Cricket Bat",
    "productPrice": "999"
}
```

----------

http://localhost:8080/api/products/list

```json
[
    {
        "id": 1,
        "productName": "Mac OS",
        "productPrice": "100"
    },
    {
        "id": 2,
        "productName": "Cricket Bat",
        "productPrice": "999"
    }
]
```

----

```
@Prateeks-MacBook-Pro lib % docker ps                        
CONTAINER ID   IMAGE             COMMAND                  CREATED         STATUS         PORTS                               NAMES
86537190eaec   spring-boot-app   "java -jar app.jar"      8 minutes ago   Up 9 seconds   0.0.0.0:8080->8080/tcp              docker-mysql-springboot-demo-app
140cb9848042   mysql:8.0.32      "docker-entrypoint.s…"   8 minutes ago   Up 9 seconds   0.0.0.0:3306->3306/tcp, 33060/tcp   spring-boot-mysql-docker-compose-kmysql-1
@Prateeks-MacBook-Pro lib % 
```

```
@Prateeks-MacBook-Pro lib % **docker exec -it 140cb9848042 bash**

bash-4.4# mysql -u root -p
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 18
Server version: 8.0.32 MySQL Community Server - GPL

Copyright (c) 2000, 2023, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> use test;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> show tables;
+----------------+
| Tables_in_test |
+----------------+
| product        |
+----------------+
1 row in set (0.00 sec)

mysql> select * from product;
+----+--------------+---------------+
| id | product_name | product_price |
+----+--------------+---------------+
|  1 | Mac OS       | 100           |
|  2 | Cricket Bat  | 999           |
+----+--------------+---------------+
2 rows in set (0.00 sec)

mysql>
```



