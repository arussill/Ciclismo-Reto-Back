package com.co.sofka.ciclismoretoback.models;

import com.co.sofka.ciclismoretoback.collections.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

    private String teamId;

    @NotBlank(message = "El codigo del equpipo es requerido")
    @Indexed(unique = true)
    @Size(min = 1, max = 3, message = "El codigo del equipo debe ser de 1 a 3 caracteres")
    private String codeTeam;

    @NotBlank(message = "El nombre del equipo es requerido")
    private String name;

    @NotBlank(message = "El pais del equipo es requerido")
    private String country;

    private Set<Cyclist> cyclists;

    public TeamDTO(String teamId, String name, String codeTeam, String country) {
        this.teamId = teamId;
        this.name = name;
        this.codeTeam = codeTeam;
        this.country = country;
    }

    public Set<Cyclist> getCyclists() {
        this.cyclists = Optional.ofNullable(cyclists).orElse(new HashSet<>());
        return cyclists;
    }
}
