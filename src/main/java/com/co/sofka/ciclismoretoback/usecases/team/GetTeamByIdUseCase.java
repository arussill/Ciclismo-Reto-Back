package com.co.sofka.ciclismoretoback.usecases.team;

import com.co.sofka.ciclismoretoback.mappers.CyclistMapper;
import com.co.sofka.ciclismoretoback.mappers.TeamMapper;
import com.co.sofka.ciclismoretoback.models.CyclistDTO;
import com.co.sofka.ciclismoretoback.models.TeamDTO;
import com.co.sofka.ciclismoretoback.repository.CyclistRepository;
import com.co.sofka.ciclismoretoback.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetTeamByIdUseCase implements Function<String, Mono<TeamDTO>> {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final CyclistRepository cyclistRepository;
    private final CyclistMapper cyclistMapper;

    public GetTeamByIdUseCase(TeamRepository teamRepository, TeamMapper teamMapper, CyclistRepository cyclistRepository, CyclistMapper cyclistMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
    }

    @Override
    public Mono<TeamDTO> apply(String id) {
        Objects.requireNonNull(id, "Id es requerido");
        return teamRepository.findById(id)
                .map(teamMapper.teamToTeamDTO())
                .flatMap(mapTeamAggregate());
    }

    private Function<TeamDTO, Mono<TeamDTO>> mapTeamAggregate() {
        return projectDTO ->
                Mono.just(projectDTO).zipWith(
                        cyclistRepository.findAllCyclistsByTeamId(projectDTO.getTeamId()).map(cyclistMapper.cyclistToCyclistDTO()).collectList(),
                        (team, cyclist) -> {
                            team.setCyclists(new HashSet(cyclist) );
                            return team;
                        }
                );
    }


}
