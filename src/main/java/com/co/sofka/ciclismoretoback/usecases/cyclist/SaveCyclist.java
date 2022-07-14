package com.co.sofka.ciclismoretoback.usecases.cyclist;

import com.co.sofka.ciclismoretoback.models.CyclistDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveCyclist {
    Mono<CyclistDTO> apply(@Valid CyclistDTO cyclistDTO);
}
