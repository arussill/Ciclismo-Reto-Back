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
@Document(collection = "cyclists")
public class Cyclist {

    @Id
    private String cyclistId;
    @Indexed(unique = true)
    private Integer riderNumber;
    private String name;
    private String lastName;
    //TeamId hace referencia al id que genera mongoDB para cada equipo
    private String teamId;
    //TeamCode hace referencia al codigo que se ingresa alfanumerico de 3 caracteres max
    private String teamCode;
    private String nationality;


}
