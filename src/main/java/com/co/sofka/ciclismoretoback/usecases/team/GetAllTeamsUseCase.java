package com.co.sofka.ciclismoretoback.usecases.team;

import com.co.sofka.ciclismoretoback.collections.Cyclist;
import com.co.sofka.ciclismoretoback.mappers.CyclistMapper;
import com.co.sofka.ciclismoretoback.mappers.TeamMapper;
import com.co.sofka.ciclismoretoback.models.TeamDTO;
import com.co.sofka.ciclismoretoback.repository.CyclistRepository;
import com.co.sofka.ciclismoretoback.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@Validated
public class GetAllTeamsUseCase implements Supplier<Flux<TeamDTO>> {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
//    private final CyclistRepository cyclistRepository;
//    private final CyclistMapper cyclistMapper;

    public GetAllTeamsUseCase(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
//        this.cyclistRepository = cyclistRepository;
//        this.cyclistMapper = cyclistMapper;
    }

    @Override
    public Flux<TeamDTO> get() {
        return teamRepository.findAll().map(teamMapper.teamToTeamDTO());
    }

//    private Function<TeamDTO, Mono<TeamDTO>> mapCyclistAggregate(){
//        return teamDTO ->
//                Mono.just(teamDTO).zipWith(
//                       cyclistRepository.findById(teamDTO.getTeamId()).map(cyclistMapper.cyclistToCyclistDTO()),
//                        (team, cyclist) -> {
//                            team.setCyclists((Set<Cyclist>) cyclist);
//                            return team;
//                        }
//                );
//    }
}
