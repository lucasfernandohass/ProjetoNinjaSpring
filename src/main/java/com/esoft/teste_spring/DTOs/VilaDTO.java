package com.esoft.teste_spring.DTOs;

import java.util.List;

import com.esoft.teste_spring.models.Vila;
import com.esoft.teste_spring.models.Ninja;

public record VilaDTO(
        Long id,
        String nome,
        int habitantes,
        List<Ninja> ninjas) {

    public VilaDTO(Vila vila) {
        this(
                vila.getId(),
                vila.getNome(),
                vila.getHabitantes(),
                vila.getNinjas());
    }

}
