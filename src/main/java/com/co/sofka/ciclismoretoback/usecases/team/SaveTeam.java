package com.co.sofka.ciclismoretoback.usecases.team;

import com.co.sofka.ciclismoretoback.models.TeamDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveTeam {
    Mono<TeamDTO> apply(@Valid TeamDTO teamDTO);
}
