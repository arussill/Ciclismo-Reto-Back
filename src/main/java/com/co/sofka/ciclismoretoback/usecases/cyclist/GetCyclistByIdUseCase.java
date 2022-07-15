package com.co.sofka.ciclismoretoback.usecases.cyclist;

import com.co.sofka.ciclismoretoback.mappers.CyclistMapper;
import com.co.sofka.ciclismoretoback.models.CyclistDTO;
import com.co.sofka.ciclismoretoback.repository.CyclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetCyclistByIdUseCase implements Function<String, Mono<CyclistDTO>> {

    private final CyclistRepository cyclistRepository;
    private final CyclistMapper cyclistMapper;

    public GetCyclistByIdUseCase(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper) {
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
    }

    @Override
    public Mono<CyclistDTO> apply(String id) {
        Objects.requireNonNull(id, "Id es requerido");
        return cyclistRepository.findById(id)
                .map(cyclistMapper.cyclistToCyclistDTO());
    }
}
