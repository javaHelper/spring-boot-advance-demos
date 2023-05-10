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