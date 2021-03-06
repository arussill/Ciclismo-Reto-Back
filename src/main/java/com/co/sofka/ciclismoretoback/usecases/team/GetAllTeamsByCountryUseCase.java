package com.co.sofka.ciclismoretoback.usecases.team;

import com.co.sofka.ciclismoretoback.mappers.TeamMapper;
import com.co.sofka.ciclismoretoback.models.TeamDTO;
import com.co.sofka.ciclismoretoback.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@Validated
public class GetAllTeamsByCountryUseCase implements Function<String, Flux<TeamDTO>> {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public GetAllTeamsByCountryUseCase(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public Flux<TeamDTO> apply(String country) {
        return teamRepository.findAllTeamsByCountry(country)
                .map(teamMapper.teamToTeamDTO());
    }
}
