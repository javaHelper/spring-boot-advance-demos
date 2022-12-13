# Event Carried State Transfer 

<img width="653" alt="Screenshot 2022-12-13 at 4 31 21 PM" src="https://user-images.githubusercontent.com/54174687/207300618-50d641f6-bcf8-42c8-b5ac-4f196aed9668.png">

- Sample Data in Mongo

```json
{
    _id: ObjectId('63984dbb7f7605000639f45e'),
    user: {
        _id: 1,
        firstname: 'Laxmi',
        lastname: 'Parate',
        email: 'laxmi.parate@gmail.com'
    },
    product: {
        _id: 0,
        description: 'ipad'
    },
    price: 300,
    _class: 'com.example.orderservice.entity.PurchaseOrder'
}
```

- Http Post Request


```sh
curl --location --request PUT 'http://localhost:8002/user-service/update' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id" :1,
    "firstname" : "Laxmi",
    "lastname": "Parate",
    "email" :"laxmi.parate@hotmail2222.com"
}'
```

```json
{
    _id: ObjectId('63984dbb7f7605000639f45e'),
    user: {
        _id: 1,
        firstname: 'Laxmi',
        lastname: 'Parate',
        email: 'laxmi.parate@hotmail2222.com'
    },
    product: {
        _id: 0,
        description: 'ipad'
    },
    price: 300,
    _class: 'com.example.orderservice.entity.PurchaseOrder'
}
```
