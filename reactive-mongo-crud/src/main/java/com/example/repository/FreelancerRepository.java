package com.example.repository;

import com.example.entity.Freelancer;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface FreelancerRepository extends ReactiveMongoRepository<Freelancer, String> {
    @Query("{ 'skills': { $all: ?0 } }")
    Flux<Freelancer> findBySkillsAll(List<String> skills);

    Flux<Freelancer> findBySkillsIn(List<String> skills);
}