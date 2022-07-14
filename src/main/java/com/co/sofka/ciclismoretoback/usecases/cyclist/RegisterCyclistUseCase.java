package com.co.sofka.ciclismoretoback.usecases.cyclist;

import com.co.sofka.ciclismoretoback.mappers.CyclistMapper;
import com.co.sofka.ciclismoretoback.models.CyclistDTO;
import com.co.sofka.ciclismoretoback.repository.CyclistRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

//@Service
//@Validated
//public class RegisterCyclistUseCase implements SaveCyclist{
//
//    private final CyclistRepository cyclistRepository;
//    private final CyclistMapper cyclistMapper;
//
//    public RegisterCyclistUseCase(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper) {
//        this.cyclistRepository = cyclistRepository;
//        this.cyclistMapper = cyclistMapper;
//    }
//
//    @Override
//    public Mono<CyclistDTO> apply(CyclistDTO cyclistDTO) {
//        return cyclistRepository.save(cyclistMapper.CyclistDTOToCyclist(null).apply(cyclistDTO))
//                .flatMap(cyclist -> teamRepository.findByID)
//    }
//}
