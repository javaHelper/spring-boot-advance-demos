package com.example.service;

import com.example.entity.Freelancer;
import com.example.projection.SkilledPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FreelancerService {
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    public Flux<SkilledPeople> getSkilledPeople() {
        //Unwind
        UnwindOperation unwindOperation = Aggregation.unwind("skills");

        //group
        GroupOperation groupOperation = Aggregation.group("skills").push("name").as("names");

        // Project
        ProjectionOperation projectionOperation = Aggregation.project("names").and("_id").as("skill");

        TypedAggregation<Freelancer> typedAggregation = Aggregation.newAggregation(
                Freelancer.class,
                unwindOperation,
                groupOperation,
                projectionOperation);

        return this.reactiveMongoTemplate.aggregate(typedAggregation, SkilledPeople.class);
    }

}