package com.co.sofka.ciclismoretoback.usecases.team;

import com.co.sofka.ciclismoretoback.mappers.TeamMapper;
import com.co.sofka.ciclismoretoback.models.TeamDTO;
import com.co.sofka.ciclismoretoback.repository.TeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Objects;

@Service
@Valid
public class UpdateTeamByIdUseCase implements SaveTeam{

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public UpdateTeamByIdUseCase(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public Mono<TeamDTO> apply(TeamDTO updatedTeam) {
        Objects.requireNonNull(updatedTeam, "Requiere id del equipo para actualizar");
        return teamRepository
                .save(teamMapper.teamDTOToTeam(updatedTeam.getTeamId())
                        .apply(updatedTeam))
                .thenReturn(updatedTeam);
    }
}
