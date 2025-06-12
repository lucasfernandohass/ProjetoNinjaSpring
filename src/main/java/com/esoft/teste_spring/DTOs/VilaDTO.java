package com.esoft.teste_spring.DTOs;

import com.esoft.teste_spring.models.Vila;

public record VilaDTO(
        Long id,
        String nome,
        int habitantes
        ){

    public VilaDTO(Vila vila) {
        this(
                vila.getId(),
                vila.getNome(),
                vila.getHabitantes()
            );
    }

}
