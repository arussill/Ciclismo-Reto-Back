package com.co.sofka.ciclismoretoback.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CyclistDTO {

    private String cyclistId;
    private Integer riderNumber;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    private String teamId;

    private String nationality;
}
