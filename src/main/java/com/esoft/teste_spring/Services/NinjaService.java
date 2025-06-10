package com.esoft.teste_spring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.esoft.teste_spring.DTOs.NinjaDTO;
import com.esoft.teste_spring.Exceptions.NaoEncontradoException;
import com.esoft.teste_spring.models.Missao;
import com.esoft.teste_spring.models.Ninja;
import com.esoft.teste_spring.repositories.MissaoRepository;
import com.esoft.teste_spring.repositories.NinjaRepository;

import jakarta.transaction.Transactional;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final MissaoRepository missaoRepository;

    public NinjaService(NinjaRepository ninjaRepository, MissaoRepository missaoRepository) {
        this.ninjaRepository = ninjaRepository;
        this.missaoRepository = missaoRepository;
    }

    public List<NinjaDTO> listar() {
        return ninjaRepository.findAll().stream().map(ninja -> new NinjaDTO(ninja)).toList();
    }

    public NinjaDTO salvar(NinjaDTO ninja) {

        Ninja ninjaEntity = new Ninja(ninja);

        if (ninja.missaoId() != null) {
            Missao missao = missaoRepository.findById(ninja.missaoId()).orElseThrow(
                    () -> new NaoEncontradoException("Missão com id " + ninja.missaoId() + " não foi encontrada!"));
            ninjaEntity.setMissao(missao);
        }

        return new NinjaDTO(ninjaRepository.save(ninjaEntity));
    }

    @Transactional
    public NinjaDTO salvar(Long id, NinjaDTO ninja) {
        ninjaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Ninja com id " + id + " não foi encontrado!"));

        Ninja ninjaEntity = new Ninja(ninja);
        ninjaEntity.setId(id);

        if (ninja.missaoId() != null) {
            Missao missao = missaoRepository.findById(ninja.missaoId()).orElse(null);
            ninjaEntity.setMissao(missao);
        }

        return new NinjaDTO(ninjaRepository.save(ninjaEntity));
    }

    public NinjaDTO buscarPorId(Long id) {
        return new NinjaDTO(ninjaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Ninja com id " + id + " não foi encontrado!")));
    }

    public void deletar(Long id) {
        ninjaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Ninja com id " + id + " não foi encontrado!"));
        ninjaRepository.deleteById(id);
    }

}
