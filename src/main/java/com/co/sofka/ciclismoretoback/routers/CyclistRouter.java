package com.co.sofka.ciclismoretoback.routers;

import com.co.sofka.ciclismoretoback.models.CyclistDTO;
import com.co.sofka.ciclismoretoback.usecases.cyclist.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CyclistRouter {

    //Registrar un nuevo ciclista
    @Bean
    public RouterFunction<ServerResponse> registerCyclist(RegisterCyclistUseCase registerCyclistUseCase) {
        Function<CyclistDTO, Mono<ServerResponse>> execute = cyclistDTO ->
                registerCyclistUseCase.apply(cyclistDTO)
                        .flatMap(cyclist -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(cyclist));
        return route(POST("/registerCyclist").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CyclistDTO.class).flatMap(execute)
        );
    }

    //Consultar todos los ciclistas
    @Bean
    public RouterFunction<ServerResponse> getAllCyclists(GetAllCyclistsUseCase getAllCyclistsUseCase) {
        return route(GET("/getAllCyclists"), request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getAllCyclistsUseCase.get(), CyclistDTO.class)));
    }

    //Consultar un ciclista por id
    @Bean
    public RouterFunction<ServerResponse> getCyclistById(GetCyclistByIdUseCase getCyclistByIdUseCase) {
        return route(
                GET("/getCyclistById/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getCyclistByIdUseCase.apply(request.pathVariable("id")),
                                CyclistDTO.class
                        ))
        );

    }

    //Actualizar un ciclista por id
    @Bean
    public RouterFunction<ServerResponse> updateCyclist(UpdateCyclistByIdUseCase updateCyclistByIdUseCase) {
        Function<CyclistDTO, Mono<ServerResponse>> executor = cyclistDTO -> updateCyclistByIdUseCase.apply(cyclistDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/updateCyclist/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CyclistDTO.class).flatMap(executor)
        );
    }

    //Eliminar un ciclista por id
    @Bean
    public RouterFunction<ServerResponse> deleteCyclist(DeleteCyclistUseCase deleteCyclistUseCase) {
        return route(
                DELETE("/deleteCyclist/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteCyclistUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }

    //Consultar todos los ciclistas por el codigo del equipo
    @Bean
    public RouterFunction<ServerResponse> getAllCyclistsByTeamId(GetAllCyclistsByTeamCodeUseCase getAllCyclistsByTeamCodeUseCase) {
        return route(
                GET("/getAllCyclistsByTeamCode/{code}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getAllCyclistsByTeamCodeUseCase.apply(request.pathVariable("code")),
                                CyclistDTO.class
                        ))
        );
    }
}
