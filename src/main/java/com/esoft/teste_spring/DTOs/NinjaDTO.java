package com.esoft.teste_spring.DTOs;

import com.esoft.teste_spring.models.Ninja;

public record NinjaDTO(
        Long id,
        String nome,
        int idade,
        String cla,
        String vila,
        Long missaoId) {

    public NinjaDTO(Ninja ninja) {
        this(
                ninja.getId(),
                ninja.getNome(),
                ninja.getIdade(),
                ninja.getCla(),
                ninja.getVila(),
                ninja.getMissao() != null ? ninja.getMissao().getId() : null);
    }
}