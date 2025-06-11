package com.esoft.teste_spring.models;

import java.util.List;

import com.esoft.teste_spring.DTOs.JutsuDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_jutsu")
public class Jutsu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private String dificuldade;

    @ManyToMany(mappedBy = "jutsu")
    private List<Ninja> ninjas;

    public Jutsu(JutsuDTO jutsoDTO) {
        this.id = jutsoDTO.id();
        this.nome = jutsoDTO.nome();
        this.descricao = jutsoDTO.descricao();
        this.dificuldade = jutsoDTO.dificuldade();
    }
}