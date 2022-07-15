package com.co.sofka.ciclismoretoback.usecases.team;

import com.co.sofka.ciclismoretoback.collections.Team;
import com.co.sofka.ciclismoretoback.mappers.TeamMapper;
import com.co.sofka.ciclismoretoback.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RegisterTeamUseCaseTest {
    @Mock
    TeamRepository teamRepository;

    TeamMapper teamMapper;

    RegisterTeamUseCase registerTeamUseCase;

    @BeforeEach
    void setUp() {
        teamMapper = new TeamMapper();
        registerTeamUseCase = new RegisterTeamUseCase(teamRepository, teamMapper);
    }

    @Test
    void HappyTest(){
        var team = new Team();
        team.setName("Equipo1");
        team.setCodeTeam("CO1");
        team.setCountry("Colombia");

        var teamReturn = new Team();
        teamReturn.setTeamId("1");
        teamReturn.setName("Equipo1");
        teamReturn.setCodeTeam("CO1");
        teamReturn.setCountry("Colombia");

        var teamDTO = teamMapper.teamToTeamDTO().apply(team);

        when(teamRepository.save(any(Team.class))).thenReturn(Mono.just(teamReturn));

        StepVerifier.create(registerTeamUseCase.apply(teamDTO))
                        .expectNextMatches(teamDTO1 -> {
                            assertEquals(teamDTO1.getTeamId(), teamReturn.getTeamId());
                            assertEquals(teamDTO1.getName(), teamReturn.getName());
                            assertEquals(teamDTO1.getCodeTeam(), teamReturn.getCodeTeam());
                            assertEquals(teamDTO1.getCountry(), teamReturn.getCountry());
                            return true;
                        }).verifyComplete();

        verify(teamRepository).save(any(Team.class));
    }

}