package com.esoft.teste_spring.DTOs;

import java.util.List;

import com.esoft.teste_spring.models.Jutsu;
import com.esoft.teste_spring.models.Ninja;

public record NinjaDTO(
        Long id,
        String nome,
        int idade,
        String cla,
        Long vilaId,
        List<Long> jutsuIds,
        Long missaoId) {

    public NinjaDTO(Ninja ninja){
        this(
                ninja.getId(),
                ninja.getNome(),
                ninja.getIdade(),
                ninja.getCla(),
                ninja.getVila() != null ? ninja.getVila().getId() : null,
                ninja.getJutsu() != null ? ninja.getJutsu().stream().map(Jutsu::getId).toList() : null,
                ninja.getMissao() != null ? ninja.getMissao().getId() : null
        );
    }
}