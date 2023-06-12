#

https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.data

https://spring.getdocs.org/en-US/spring-boot-docs/boot-features/boot-features-nosql.html#boot-features-elasticsearch

https://docs.spring.io/spring-data/elasticsearch/docs/current-SNAPSHOT/reference/html/#reference

https://docs.spring.io/spring-data/elasticsearch/docs/5.0.6/reference/html/


```
{
  "properties": {
    "account_number": {
      "type": "long"
    },
    "address": {
      "type": "text"
    },
    "age": {
      "type": "long"
    },
    "balance": {
      "type": "long"
    },
    "city": {
      "type": "text"
    },
    "email": {
      "type": "text"
    },
    "employer": {
      "type": "text"
    },
    "firstname": {
      "type": "text"
    },
    "gender": {
      "type": "keyword"
    },
    "index": {
      "type": "object"
    },
    "lastname": {
      "type": "text"
    },
    "state": {
      "type": "keyword"
    }
  }
}
```


# Find By Lastname

http://localhost:8080/accounts/Smith

````json
[
    {
        "id": "F3-9fIgBhfTLJt8QzMf_",
        "account_number": 516,
        "balance": 44940,
        "firstname": "Roy",
        "lastname": "Smith",
        "age": 37,
        "gender": "M",
        "address": "770 Cherry Street",
        "employer": "Parleynet",
        "email": "roysmith@parleynet.com",
        "city": "Carrsville",
        "state": "RI"
    }
]
````

# Find By Age 

http://localhost:8080/accounts/age/32

# Find By Firstname

http://localhost:8080/accounts/firstname/huff dale

# Find in firstname or address

http://localhost:8080/accounts/multi-match/ford

# Find By Match Phrase

http://localhost:8080/accounts/match-phrase/Sedgwick street

# Find By wild card

http://localhost:8080/accounts/wild-card/h*ll

# Term Query

http://localhost:8080/accounts/term/street

# Compound Query
http://localhost:8080/accounts/term/street

http://localhost:8080/accounts/compound-query1/F

http://localhost:8080/accounts/compound-query2/street/27

# FUZZY Search

http://localhost:8080/accounts/fuzzy/edenburg

# Range DSL

http://localhost:8080/accounts/range-dsl/10000

# Range DSL2

http://localhost:8080/accounts/range-dsl2/30/40/il