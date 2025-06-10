package com.esoft.teste_spring.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.esoft.teste_spring.DTOs.NinjaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    private String vila;

    @ManyToOne
    @JoinColumn(name = "missao_id")
    private Missao missao;

    public Ninja(NinjaDTO ninja) {
        this.id = ninja.id();
        this.nome = ninja.nome();
        this.idade = ninja.idade();
        this.cla = ninja.cla();
        this.vila = ninja.vila();
    }

}
