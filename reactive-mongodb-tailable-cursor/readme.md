# Creating Capped Collection:

Enter into the mongo container

```
docker exec -it mongo bash
```

Access mongo shell for the ‘admin’ database.

```
mongosh admin --username admin --password password
```

Create a capped collection called movie. Here the size is max number of bytes for the collection documents which is a mandatory field. Max is 50 documents – it is optional.

```
db.createCollection("movie", { capped : true, size : 5242880, max : 50 } )
```

Once you start and launch the app

```
{
    "title":"Code Name: K.O.Z.",
    "year":2015,
    "genre":"action",
    "imdbRating":1.6
  }
```
<img width="1118" alt="Screenshot 2023-05-10 at 11 25 20 PM" src="https://github.com/javaHelper/spring-boot-advance-demos/assets/54174687/0f298b4e-364b-468d-960e-83511337a9bb">

---------------

<img width="1102" alt="Screenshot 2023-05-10 at 11 25 31 PM" src="https://github.com/javaHelper/spring-boot-advance-demos/assets/54174687/32c0687d-7c91-4bfd-9c48-4664d003fd3f">

