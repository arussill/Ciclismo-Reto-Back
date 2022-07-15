package com.co.sofka.ciclismoretoback.repository;

import com.co.sofka.ciclismoretoback.collections.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CyclistRepository extends ReactiveMongoRepository<Cyclist, String> {
    Mono<Cyclist> findByTeamId(String id);
    Flux<Cyclist> findAllCyclistsByTeamId(String id);

}
