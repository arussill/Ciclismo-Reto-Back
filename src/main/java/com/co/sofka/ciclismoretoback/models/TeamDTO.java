package com.co.sofka.ciclismoretoback.models;

import com.co.sofka.ciclismoretoback.collections.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

    private String teamId;

    private String codeTeam;

    @NotBlank
    private String name;

    @NotBlank
    private String country;

    @NotNull
    @Length.List( {
        @Length(min = 1, message = "Debe tener al menos un ciclista"),
        @Length(max = 4, message = "Debe tener máximo 8 ciclistas")
    } )
    private List<Cyclist> cyclists;
}
