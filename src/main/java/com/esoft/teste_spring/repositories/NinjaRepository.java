package com.esoft.teste_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esoft.teste_spring.models.Ninja;
import java.util.List;

@Repository
public interface NinjaRepository extends JpaRepository<Ninja, Long> {

    List<Ninja> findByCla(String cla);

    List<Ninja> findByIdadeGreaterThan(int idade);

    List<Ninja> findByNomeContainingIgnoreCase(String nome);

}
