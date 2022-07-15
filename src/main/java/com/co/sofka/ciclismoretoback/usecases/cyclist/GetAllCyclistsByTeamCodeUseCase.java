package com.co.sofka.ciclismoretoback.usecases.cyclist;


import com.co.sofka.ciclismoretoback.mappers.CyclistMapper;
import com.co.sofka.ciclismoretoback.models.CyclistDTO;
import com.co.sofka.ciclismoretoback.repository.CyclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@Validated
public class GetAllCyclistsByTeamCodeUseCase implements Function <String, Flux<CyclistDTO>> {

    private final CyclistRepository cyclistRepository;
    private final CyclistMapper cyclistMapper;

    public GetAllCyclistsByTeamCodeUseCase(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper) {
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
    }


    @Override
    public Flux<CyclistDTO> apply(String teamCode) {
        return cyclistRepository.findAllCyclistsByTeamCode(teamCode)
                .map(cyclistMapper.cyclistToCyclistDTO());
    }
}
