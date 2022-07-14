package com.co.sofka.ciclismoretoback.usecases.cyclist;

import com.co.sofka.ciclismoretoback.mappers.CyclistMapper;
import com.co.sofka.ciclismoretoback.models.CyclistDTO;
import com.co.sofka.ciclismoretoback.repository.CyclistRepository;
import com.co.sofka.ciclismoretoback.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class RegisterCyclistUseCase implements SaveCyclist{

    private final CyclistRepository cyclistRepository;
    private final CyclistMapper cyclistMapper;
    private final TeamRepository teamRepository;

    public RegisterCyclistUseCase(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper, TeamRepository teamRepository) {
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
        this.teamRepository = teamRepository;
    }

    @Override
    public Mono<CyclistDTO> apply(CyclistDTO cyclistDTO) {
        return cyclistRepository.save(cyclistMapper.cyclistDTOToCyclist(null).apply(cyclistDTO))
                .map(cyclist -> cyclistMapper.cyclistToCyclistDTO().apply(cyclist));
    }
}
