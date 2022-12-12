# Reactive MongoDB Crud

I register few freelancers as shown here using the POST endpoint.

````
{
    "name": "sam",
    "age": 40,
    "skills": [ "js", "react", "python"]
}
{
    "name": "jack",
    "age": 38,
    "skills": [ "js", "angular", "postgres"]
}

{
    "name": "james",
    "age": 30,
    "skills": [ "java", "reactor", "mongo"]
}

{
    "name": "smith",
    "age": 32,
    "skills": [ "qa", "selenium"]
}
````

- List All persons => http://localhost:8080/person
- List by Skills   => http://localhost:8080/person/skills-one/js,react, http://localhost:8080/person/skills-one/angular,reactor
- Find Person By Id => http://localhost:8080/person/6396d356023a55600a994373