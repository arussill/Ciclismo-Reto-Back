package com.co.sofka.ciclismoretoback.usecases.cyclist;

import com.co.sofka.ciclismoretoback.mappers.CyclistMapper;
import com.co.sofka.ciclismoretoback.models.CyclistDTO;
import com.co.sofka.ciclismoretoback.repository.CyclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class GetAllCyclistsUseCase implements Supplier<Flux<CyclistDTO>> {

    private final CyclistRepository cyclistRepository;
    private final CyclistMapper cyclistMapper;

    public GetAllCyclistsUseCase(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper) {
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
    }

    @Override
    public Flux<CyclistDTO> get() {
        return cyclistRepository.findAll().map(cyclistMapper.cyclistToCyclistDTO());
    }

}
