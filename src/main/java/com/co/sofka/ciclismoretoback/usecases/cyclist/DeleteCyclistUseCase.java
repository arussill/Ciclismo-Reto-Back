package com.co.sofka.ciclismoretoback.usecases.cyclist;

import com.co.sofka.ciclismoretoback.repository.CyclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DeleteCyclistUseCase implements Function<String, Mono<Void>> {

    private final CyclistRepository cyclistRepository;

    public DeleteCyclistUseCase(CyclistRepository cyclistRepository) {
        this.cyclistRepository = cyclistRepository;
    }
    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "El id del ciclista es requerido para eleiminarlo");
        return cyclistRepository.deleteById(id);
    }
}
