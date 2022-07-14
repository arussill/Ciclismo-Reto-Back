package com.co.sofka.ciclismoretoback.routers;

import com.co.sofka.ciclismoretoback.models.TeamDTO;
import com.co.sofka.ciclismoretoback.usecases.team.*;
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
public class TeamRouter {

    //Registrar un nuevo equipo
    @Bean
    public RouterFunction<ServerResponse> registerTeam(RegisterTeamUseCase registerTeamUseCase) {
        Function<TeamDTO, Mono<ServerResponse>> execute = teamDTO ->
                registerTeamUseCase.apply(teamDTO)
                        .flatMap(team -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).bodyValue(team));
        return route(POST("/registerTeam").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDTO.class).flatMap(execute)
        );
    }

    //Consultar todos los equipos
    @Bean
    public RouterFunction<ServerResponse> getAllTeams(GetAllTeamsUseCase getAllTeamsUseCase) {
        return route(GET("/getAllTeams"), request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getAllTeamsUseCase.get(), TeamDTO.class)));
    }

    //Consultar un equipo por id
    @Bean
    public RouterFunction<ServerResponse> getTeamById(GetTeamByIdUseCase getTeamByIdUseCase){
        return route(
                GET("/getTeamById/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getTeamByIdUseCase.apply(request.pathVariable("id")),
                                TeamDTO.class
                        ))
        );

    }

    //Actualizar un equipo por id
    @Bean
    public RouterFunction<ServerResponse> updateTeam(UpdateTeamByIdUseCase updateTeamByIdUseCase) {
        Function<TeamDTO, Mono<ServerResponse>> executor = teamDTO -> updateTeamByIdUseCase.apply(teamDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/updateTeam/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDTO.class).flatMap(executor)
        );
    }

    //Eliminar un equipo por id
    @Bean
    public RouterFunction<ServerResponse> deleteTeam(DeleteTeamUseCase deleteTeamUseCase) {
        return route(
                DELETE("/deleteTeam/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteTeamUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }

}