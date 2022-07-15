package com.co.sofka.ciclismoretoback.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CyclistDTO {

    private String cyclistId;

    @NotNull(message = "El numero del competidor es requerido")
    @Indexed(unique = true)
    @Min(value = 1, message = "El numero del competidor debe ser  minimo 1")
    @Max(value = 999, message = "El numero del competidor debe ser  maximo de 3 digitos")
    private Integer riderNumber;

    @NotBlank(message = "El nombre del ciclista es requerido")
    private String name;

    @NotBlank(message = "El apellido del ciclista es requerido")
    private String lastName;

    @NotBlank(message = "El id entregado por mongoDB del equipo es requerido")
    private String teamId;

    @NotBlank(message = "El codigo del equipo es requerido")
    @Size(min = 1, max = 3, message = "El codigo del equipo debe ser alfanumerico de 1 a 3 caracteres")
    private String teamCode;

    @NotBlank(message = "La nacionalidad del competidor es requerida")
    private String nationality;
}
