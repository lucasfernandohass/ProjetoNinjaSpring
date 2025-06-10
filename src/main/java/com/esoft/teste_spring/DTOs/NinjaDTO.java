package com.esoft.teste_spring.DTOs;

import com.esoft.teste_spring.models.Ninja;

import java.util.List;

import com.esoft.teste_spring.models.Jutsu;

public record NinjaDTO(
        Long id,
        String nome,
        int idade,
        String cla,
        Long vilaId,
        List<Jutsu> jutsu,
        Long missaoId) {

    public NinjaDTO(Ninja ninja) {
        this(
                ninja.getId(),
                ninja.getNome(),
                ninja.getIdade(),
                ninja.getCla(),
                ninja.getVila() != null ? ninja.getVila().getId() : null,
                ninja.getJutsu(),
                ninja.getMissao() != null ? ninja.getMissao().getId() : null);
    }
}