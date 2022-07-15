package com.co.sofka.ciclismoretoback.usecases.cyclist;

import com.co.sofka.ciclismoretoback.mappers.CyclistMapper;
import com.co.sofka.ciclismoretoback.models.CyclistDTO;
import com.co.sofka.ciclismoretoback.repository.CyclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UpdateCyclistByIdUseCase implements SaveCyclist{

    private final CyclistRepository cyclistRepository;
    private final CyclistMapper cyclistMapper;

    public UpdateCyclistByIdUseCase(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper) {
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
    }

    @Override
    public Mono<CyclistDTO> apply(CyclistDTO updateCyclist) {
        Objects.requireNonNull(updateCyclist, "Requiere id del equipo para actualizar");
        return cyclistRepository.save(cyclistMapper.cyclistDTOToCyclist(null).apply(updateCyclist))
                .map(cyclist -> cyclistMapper.cyclistToCyclistDTO().apply(cyclist));
    }
}
