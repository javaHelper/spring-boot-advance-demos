# Spring Boot Jasypt

# Find All Products
```
http://localhost:8080/products
```

# Find Product By Id

```
http://localhost:8080/productById/1
```

# Find Product By Name

```

```

- application.yml

```sh
spring:
  datasource:
    username: ENC(uF0uIOFxdoKlLNq1kfH7Ag==)
    password: ENC(ylxeHR9lJ5Yq453w2Ovmd2DGijnr0STZ)
    url: jdbc:mysql://localhost:3306/test?createDatabaseIfNotExist=true&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
    properties.hibernate.dialect: org.hibernate.dialect.MySQL5Dialect
```
