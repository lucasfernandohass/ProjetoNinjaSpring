package com.esoft.teste_spring.models;

import java.util.List;

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

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_vila")
public class Vila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int habitantes;

    @OneToMany(mappedBy = "vila")
    private List<Ninja> ninjas;

    /*
     * public Vila(VilaDTO vila){
     * this.id = vila.id();
     * this.nome = vila.nome();
     * this.habitantes = vila.habitantes();
     * }
     */
}