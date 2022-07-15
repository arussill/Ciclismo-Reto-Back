package com.co.sofka.ciclismoretoback.routers;

import com.co.sofka.ciclismoretoback.models.TeamDTO;
import com.co.sofka.ciclismoretoback.usecases.cyclist.GetAllCyclistsUseCase;
import com.co.sofka.ciclismoretoback.usecases.cyclist.RegisterCyclistUseCase;
import com.co.sofka.ciclismoretoback.usecases.cyclist.UpdateCyclistByIdUseCase;
import com.co.sofka.ciclismoretoback.usecases.team.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
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
    @RouterOperation(beanClass = RegisterTeamUseCase.class, beanMethod = "apply", operation = @Operation(operationId = "registerTeam", summary = "Registrar Equipo", tags = {
            "Equipo"
    }, responses = {@ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
            @ApiResponse(responseCode = "404", description = "Team not found")}))

    public RouterFunction<ServerResponse> registerTeam(RegisterTeamUseCase registerTeamUseCase) {
        Function<TeamDTO, Mono<ServerResponse>> execute = teamDTO ->
                registerTeamUseCase.apply(teamDTO)
                        .flatMap(result -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(result));
        return route(POST("/registerTeam").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDTO.class).flatMap(execute)
        );
    }

    //Consultar todos los equipos
    @Bean
    @RouterOperation(beanClass = GetAllTeamsUseCase.class, beanMethod = "get", operation = @Operation(operationId = "getAllTeams", summary = "Consultar todos los equipos", tags = {
            "Equipo"}, responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
            @ApiResponse(responseCode = "404", description = "Teams not found")}))
    public RouterFunction<ServerResponse> getAllTeams(GetAllTeamsUseCase getAllTeamsUseCase) {
        return route(GET("/getAllTeams"), request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getAllTeamsUseCase.get(), TeamDTO.class)));
    }

    //Consultar un equipo por id
    @Bean
    @RouterOperation(operation = @Operation(operationId = "getTeamById", summary = "Consultar Equipo por su id", tags = {"Equipo"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "id del Equipo")},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
                    @ApiResponse(responseCode = "404", description = "Team not found")}))
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
    @RouterOperation(beanClass = UpdateTeamByIdUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "updateTeam", summary = "Actualizar un Equipo por id", tags = {"Equipo"},
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
                            @ApiResponse(responseCode = "404", description = "Team not found")}))

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
    @RouterOperation(operation = @Operation(operationId = "delete", summary = "Borrar equipo por su id", tags = {"Equipo"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Id equipo")},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
                    @ApiResponse(responseCode = "404", description = "Team not found")}))
    public RouterFunction<ServerResponse> deleteTeam(DeleteTeamUseCase deleteTeamUseCase) {
        return route(
                DELETE("/deleteTeam/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteTeamUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }

    //Consultar un equipo por pais
    @Bean
    @RouterOperation(operation = @Operation(operationId = "getAllTeamsByCountry", summary = "Consultar equipo por nacionalidad", tags = {"Equipo"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "country", description = "equipo por pais")},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
                    @ApiResponse(responseCode = "404", description = "Team not found")}))
    public RouterFunction<ServerResponse> getAllTeamsByCountry(GetAllTeamsByCountryUseCase getAllTeamsByCountryUseCase) {
        return route(
                GET("/getAllTeamsByCountry/{country}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getAllTeamsByCountryUseCase.apply(request.pathVariable("country")),
                                TeamDTO.class
                        ))
        );
    }

}