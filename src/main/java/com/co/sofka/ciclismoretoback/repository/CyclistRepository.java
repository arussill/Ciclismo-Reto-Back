package com.co.sofka.ciclismoretoback.repository;

import com.co.sofka.ciclismoretoback.collections.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface CyclistRepository extends ReactiveMongoRepository<Cyclist, String> {
    Flux<Cyclist> findAllCyclistsByTeamId(String id);
    Flux<Cyclist> findAllCyclistsByTeamCode(String teamCode);
}
