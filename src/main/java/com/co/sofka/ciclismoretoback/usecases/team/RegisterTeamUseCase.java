package com.co.sofka.ciclismoretoback.usecases.team;

import com.co.sofka.ciclismoretoback.mappers.TeamMapper;
import com.co.sofka.ciclismoretoback.models.TeamDTO;
import com.co.sofka.ciclismoretoback.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class RegisterTeamUseCase implements SaveTeam {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public RegisterTeamUseCase(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public Mono<TeamDTO> apply(TeamDTO teamDTO) {
        return teamRepository.save((teamMapper.teamDTOToTeam(null).apply(teamDTO)))
                .map(team -> teamMapper.teamToTeamDTO().apply(team))
                .onErrorResume(error -> {
                    return Mono.error(new RuntimeException("Cycling team existente"));
                });
    }
}
