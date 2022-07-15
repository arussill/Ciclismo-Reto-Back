package com.co.sofka.ciclismoretoback.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Teams")
public class Team {

    @Id
    private String teamId;

    @NotBlank
    @Indexed(unique = true)
    @Size(min = 1, max = 3)
    private String codeTeam;

    @NotBlank
    private String name;

    @NotBlank
    private String country;

}
