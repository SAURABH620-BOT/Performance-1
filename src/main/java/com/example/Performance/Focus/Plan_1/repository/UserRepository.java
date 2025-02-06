package com.example.Performance.Focus.Plan_1.repository;

import com.example.Performance.Focus.Plan_1.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUserEmail(String email);
}
