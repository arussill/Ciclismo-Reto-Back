package com.co.sofka.ciclismoretoback.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Teams")
public class Team {

    @Id
    private String teamId;
    private String codeTeam;
    private String name;
    private String country;
    private List<Cyclist> cyclists;

}
