package com.co.sofka.ciclismoretoback.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cyclists")
public class Cyclist {

    @Id
    private String cyclistId;
    private Integer riderNumber;
    private String name;
    private String lastName;
    private String teamId;
    private String teamCode;
    private String nationality;


}
