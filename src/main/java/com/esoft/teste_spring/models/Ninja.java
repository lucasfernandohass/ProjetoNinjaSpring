package com.esoft.teste_spring.models;

import java.util.List;

import com.esoft.teste_spring.DTOs.NinjaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tb_ninja")
public class Ninja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int idade;
    private String cla;

    @ManyToOne
    @JoinColumn(name = "vila_id", nullable = false)
    private Vila vila;

    // Solução do Problema do N:N para salvar o Jutsu no Ninja
    @ManyToMany
    @JoinTable(
        name = "tb_ninja_jutsu",
        joinColumns = @JoinColumn(name = "ninja_id"),
        inverseJoinColumns = @JoinColumn(name = "jutsu_id")
    )
    private List<Jutsu> jutsu;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "missao_id")
    private Missao missao;

    public Ninja(NinjaDTO ninja) {
        this.id = ninja.id();
        this.nome = ninja.nome();
        this.idade = ninja.idade();
        this.cla = ninja.cla();
    }

}
