package com.co.sofka.ciclismoretoback.routers;

import com.co.sofka.ciclismoretoback.models.CyclistDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

//@Configuration
//public class CyclistRouter {
//
//    //Registrar un nuevo ciclista
//    @Bean
//    public RouterFunction<ServerResponse> registerCyclist(RegisterCyclistUseCase registerCyclistUseCase) {
//        Function<CyclistDTO, Mono<ServerResponse>> execute = cyclistDTO ->
//           registerCyclistUseCase.apply(cyclistDTO)
//                   .flatMap(cyclist -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).bodyValue(cyclist));
//        return route(POST("/cyclist/register").and(accept(MediaType.APPLICATION_JSON)),
//                request -> request.bodyToMono(CyclistDTO.class).flatMap(execute)
//        );
//    }
//
//
//}
