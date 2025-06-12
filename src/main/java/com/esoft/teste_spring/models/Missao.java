package com.esoft.teste_spring.models;

import java.util.List;

import com.esoft.teste_spring.DTOs.MissaoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "tb_missoes")
public class Missao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    private String descricao;
    private String dificuldade;

    @OneToMany(mappedBy = "missao")
    private List<Ninja> ninjas;

    public Missao(MissaoDTO missao) {
        this.id = missao.id();
        this.nome = missao.nome();
        this.descricao = missao.descricao();
        this.dificuldade = missao.dificuldade();
    }

}
