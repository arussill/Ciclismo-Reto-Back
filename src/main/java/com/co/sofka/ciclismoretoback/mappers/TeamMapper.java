package com.co.sofka.ciclismoretoback.mappers;

import com.co.sofka.ciclismoretoback.collections.Team;
import com.co.sofka.ciclismoretoback.models.TeamDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TeamMapper {

    public Function<TeamDTO, Team> TeamDTOToTeam(String id) {
        return teamDTO -> {
            var team = new Team();
            team.setTeamId(teamDTO.getTeamId());
            team.setName(teamDTO.getName());
            team.setCodeTeam(teamDTO.getCodeTeam());
            team.setCountry(teamDTO.getCountry());
            team.setCyclists(teamDTO.getCyclists());
            return team;
        };
    }

    public Function <Team, TeamDTO> TeamToTeamDTO() {
        return team -> new TeamDTO(
                team.getTeamId(),
                team.getName(),
                team.getCodeTeam(),
                team.getCountry(),
                team.getCyclists()
        );
    }
}
