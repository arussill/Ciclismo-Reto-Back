package com.co.sofka.ciclismoretoback.repository;

import com.co.sofka.ciclismoretoback.collections.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CyclistRepository extends ReactiveMongoRepository<Cyclist, String> {


}
