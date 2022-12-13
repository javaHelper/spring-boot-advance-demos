package com.example.repository;

import com.example.entity.Freelancer;
import com.example.projection.SkilledPeople;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface FreelancerRepository extends ReactiveMongoRepository<Freelancer, String> {

    @Aggregation({
            "{ $unwind : { path : $skills } }",
            "{ $group : { _id : $skills, names : { $push : $name } } }",
            "{ $project : { _id : 0, skill : $_id, names : 1 } }"
    })
    Flux<SkilledPeople> getSkilledPeople();
}