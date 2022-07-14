package com.co.sofka.ciclismoretoback.repository;

import com.co.sofka.ciclismoretoback.collections.Team;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends ReactiveMongoRepository<Team, String> {


}
