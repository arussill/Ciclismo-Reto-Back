package com.co.sofka.ciclismoretoback.mappers;

import com.co.sofka.ciclismoretoback.collections.Cyclist;
import com.co.sofka.ciclismoretoback.models.CyclistDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CyclistMapper {

    public Function<CyclistDTO, Cyclist> cyclistDTOToCyclist(String id) {
        return cyclistDTO -> {
            var cyclist = new Cyclist();
            cyclist.setCyclistId(cyclistDTO.getCyclistId());
            cyclist.setName(cyclistDTO.getName());
            cyclist.setLastName(cyclistDTO.getLastName());
            cyclist.setRiderNumber(cyclistDTO.getRiderNumber());
            cyclist.setTeamId(cyclistDTO.getTeamId());
            cyclist.setRiderNumber(cyclistDTO.getRiderNumber());
            return cyclist;
        };
    }

    public Function <Cyclist, CyclistDTO> cyclistToCyclistDTO() {
        return cyclist -> new CyclistDTO(
                cyclist.getCyclistId(),
                cyclist.getRiderNumber(),
                cyclist.getName(),
                cyclist.getLastName(),
                cyclist.getTeamId(),
                cyclist.getNationality()
        );
    }
}
