package com.co.sofka.ciclismoretoback.usecases.team;

import com.co.sofka.ciclismoretoback.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;


@Service
@Validated
public class DeleteTeamUseCase implements Function<String, Mono<Void>> {

    private final TeamRepository teamRepository;

    public DeleteTeamUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "El id del equipo es requerido para eliminarlo");
        return teamRepository.deleteById(id);
    }
}
