package com.esoft.teste_spring.DTOs;

import java.util.List;

import com.esoft.teste_spring.models.Jutsu;

public record JutsuDTO(
        Long id,
        String nome,
        String descricao,
        String dificuldade) {

    public JutsuDTO(Jutsu jutsu) {
        this(
                jutsu.getId(),
                jutsu.getNome(),
                jutsu.getDescricao(),
                jutsu.getDificuldade());
    }

}