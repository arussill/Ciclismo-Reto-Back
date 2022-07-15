package com.co.sofka.ciclismoretoback.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "teams")
public class Team {

    @Id
    private String teamId;
    @Indexed(unique = true)
    private String codeTeam;
    private String name;
    private String country;

}
